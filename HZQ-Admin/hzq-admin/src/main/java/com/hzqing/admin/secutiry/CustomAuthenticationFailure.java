package com.hzqing.admin.secutiry;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hzqing
 * @date 2018/10/27 19:30
 */
@Component
public class CustomAuthenticationFailure implements AuthenticationFailureHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        exception.printStackTrace();
        Map res = new HashMap();
        res.put("error","hzqingerror");
        String json = objectMapper.writeValueAsString(res);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
