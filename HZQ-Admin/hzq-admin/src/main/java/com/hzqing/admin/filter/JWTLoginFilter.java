package com.hzqing.admin.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzqing.admin.secutiry.domain.CustomUserDetails;
import com.hzqing.common.constant.Constants;
import com.hzqing.common.jwt.JwtTokenUtil;
import com.hzqing.common.util.AESUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 验证用户名密码是否正确，生成token，并将token返回客户端
 * attemptAuthentication 接受并解析用户信息
 * @author hzqing
 * @date 2018/10/28 10:09
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter{


    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接受并解析用户的信息
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CustomUserDetails user = objectMapper.readValue(request.getInputStream(), CustomUserDetails.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用户登陆成功后，这个方法会被调用，在这里生成token
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //生成jwt
        Map<String, Object> claims = new HashMap<String, Object>();
        // 获取用户登陆信息
        User user = (User) authResult.getPrincipal();

        // CustomUserDetails user = (CustomUserDetails) principal;
        //Claims包含您想要签署的任何信息
        String iss = AESUtil.AESEncode(Constants.AES_SECRET,"id"+","+user.getUsername()+","+user.getPassword());
        claims.put("iss",iss); //jwt的签发者 保存用户的帐号和密码以及id 使用AES对称加密
        claims.put("sub",user.getUsername()); // JWT所面向的用户 用户的昵称
        claims.put("iat", new Date());
        claims.put("jti", UUID.randomUUID()); //jwt的唯一身份表示
        // 生成token
        String token = JwtTokenUtil.generateToken(claims, Constants.JWT_SECRET, Constants.JWT_EXPIRATION);

        response.addHeader("Authorization","Bearer " + token);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers ","Authorization,Access-Control-Allow-Origin");

        Map res = new HashMap();
        res.put("code",200);
        res.put("Authorization","Bearer " + token);
        String json = new ObjectMapper().writeValueAsString(res);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);

    }
}
