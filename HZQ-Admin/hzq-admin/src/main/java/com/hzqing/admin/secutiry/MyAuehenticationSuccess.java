package com.hzqing.admin.secutiry;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hzqing
 * @date 2018/10/27 19:24
 */
@Component
public class MyAuehenticationSuccess implements AuthenticationSuccessHandler {
    // object 对象转json
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     * @param request
     * @param response
     * @param authentication 代表认证成功之后的信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map res = new HashMap();
        res.put("success","hzqingsuccess");
        String json = objectMapper.writeValueAsString(res);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
