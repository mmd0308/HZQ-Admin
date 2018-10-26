package com.hzqing.common.response;


import com.hzqing.common.constant.Constants;
import com.hzqing.common.jwt.JwtTokenUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResponseMessage<T> {

    private int code;
    private T data;
    private String token;

    private long total;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
    //    public ResponseMessage() {
//        if (null == generateToken()){
//            this.setToken("");
//        }else {
//            this.setToken(generateToken());
//            this.setStatus(Constants.WEB_RESPONSE_STATUES_REPLACE_TOKEN);
//        }
//    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 返回正确的结果和数据
     * @param data
     * @return
     */
    public ResponseMessage success(T data){
        this.setData(data);
        if (this.getCode() == 0 ) //不用覆盖原先的值
            this.setCode(Constants.WEB_RESPONSE_STATUES_SUCCESS);
        return this;
    }
    public ResponseMessage successPage(T data,long total){
        this.setData(data);
        this.setTotal(total);
        if (this.getCode() == 0 ) //不用覆盖原先的值
            this.setCode(Constants.WEB_RESPONSE_STATUES_SUCCESS);
        return this;
    }

    /**
     * 返回成功
     * @return
     */
    public ResponseMessage success(){
        if (this.getCode() == 0 ) //不用覆盖原先的值
            this.setCode(Constants.WEB_RESPONSE_STATUES_SUCCESS);
        return this;
    }

    public ResponseMessage message(int status, T data){
        this.setCode(status);
        this.setData(data);
        return this;
    }

    /**
     * 登录失败  登录名或者密码错误
     * @param message
     * @return
     */
    public ResponseMessage loginError(T message){
        this.setData(message);
        this.setCode(Constants.WEB_RESPONSE_STATUES_LOGIN_ERROR);
        return this;
    }


    public void setData(T data) {
        this.data = data;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public T getData() {
        return data;
    }

    public String getToken() {
        return token;
    }
    public String generateToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object replace_token = request.getAttribute(Constants.REPLACE_TOKEN_KEY);
        String token = null;
        if (null != replace_token){
            boolean rToken = Boolean.parseBoolean((String) replace_token);
            if (rToken){ //token快要过期了，替换token
                String oldToken = request.getHeader(Constants.ACCESS_TOKEN_KEY);
                // 校验token信息是否准确。在多终端登录时候密码修改需要全部重新登录
                String iss = (String) JwtTokenUtil.getObjectFromToken(oldToken, Constants.JWT_SECRET, "iss");
                String username = JwtTokenUtil.getUsernameFromToken(oldToken, Constants.JWT_SECRET);
                Map<String, Object> claims = new HashMap<String, Object>();  // Claims包含您想要签署的任何信息
                claims.put("iss",iss);// jwt的签发者 保存用户的帐号和密码以及id 使用AES对称加密
                claims.put("sub",username); // JWT所面向的用户 用户的username
                claims.put("iat", new Date());
                claims.put("jti", UUID.randomUUID()); //jwt的唯一身份表示
                //获取token
                token = JwtTokenUtil.generateToken(claims, Constants.JWT_SECRET, Constants.JWT_EXPIRATION);
            }
        }
        return token;
    }
}
