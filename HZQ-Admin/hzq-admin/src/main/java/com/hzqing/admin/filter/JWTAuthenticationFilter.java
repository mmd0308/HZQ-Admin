package com.hzqing.admin.filter;

import com.hzqing.admin.secutiry.domain.CustomUserDetails;
import com.hzqing.admin.secutiry.service.CustomUserDetailsService;
import com.hzqing.common.constant.Constants;
import com.hzqing.common.jwt.JwtTokenUtil;
import com.hzqing.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验token是否正确
 * 在JWTLoginFilter中生成token，在该拦截器中校验token是否正确
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 * @author hzqing
 * @date 2018/10/28 10:37
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter{


    private final static Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("Authorization");
        System.out.println("-------" + token);
        // token不存在，直接放行
        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        // 从token中获取用户名
        String userName = JwtTokenUtil.getUsernameFromToken(token.replace("Bearer ",""), Constants.JWT_SECRET);

        if (StringUtils.isNotEmpty(userName)) {
            // 从新配置 SecurityContextHolder
             UserDetails userDetails = new CustomUserDetails();//customUserDetailsService.loadUserByUsername(userName);
             UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
             SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else  {
            logger.error("该Token错误，请重新登陆");
        }

        chain.doFilter(request, response);

    }
}
