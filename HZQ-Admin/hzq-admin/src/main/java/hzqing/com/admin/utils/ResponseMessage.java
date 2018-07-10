package hzqing.com.admin.utils;


import hzqing.com.common.constant.Constant;
import hzqing.com.common.jwt.JwtTokenUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResponseMessage<T> {

    private int status;
    private T data;
    private String token;


    public ResponseMessage() {
        if (null == generateToken()){
            this.setToken("");
        }else {
            this.setToken(generateToken());
            this.setStatus(Constant.WEB_RESPONSE_STATUES_REPLACE_TOKEN);
        }
    }


    /**
     * 返回正确的结果和数据
     * @param data
     * @return
     */
    public ResponseMessage success(T data){
        this.setData(data);
        if (this.getStatus() == 0 ) //不用覆盖原先的值
            this.setStatus(Constant.WEB_RESPONSE_STATUES_SUCCESS);
        return this;
    }

    /**
     * 返回成功
     * @return
     */
    public ResponseMessage success(){
        if (this.getStatus() == 0 ) //不用覆盖原先的值
            this.setStatus(Constant.WEB_RESPONSE_STATUES_SUCCESS);
        return this;
    }

    public ResponseMessage message(int status, T data){
        this.setStatus(status);
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
        this.setStatus(Constant.WEB_RESPONSE_STATUES_LOGIN_ERROR);
        return this;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getToken() {
        return token;
    }
    public String generateToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object replace_token = request.getAttribute(Constant.REPLACE_TOKEN_KEY);
        String token = null;
        if (null != replace_token){
            boolean rToken = (boolean) replace_token;
            if (rToken){ //token快要过期了，替换token
                String oldToken = request.getHeader(Constant.ACCESS_TOKEN_KEY);
                // 校验token信息是否准确。在多终端登录时候密码修改需要全部重新登录
                String iss = (String) JwtTokenUtil.getObjectFromToken(oldToken, Constant.JWT_SECRET, "iss");
                String username = JwtTokenUtil.getUsernameFromToken(oldToken,Constant.JWT_SECRET);
                Map<String, Object> claims = new HashMap<String, Object>();  // Claims包含您想要签署的任何信息
                claims.put("iss",iss);// jwt的签发者 保存用户的帐号和密码以及id 使用AES对称加密
                claims.put("sub",username); // JWT所面向的用户 用户的username
                claims.put("iat", new Date());
                claims.put("jti", UUID.randomUUID()); //jwt的唯一身份表示
                //获取token
                token = JwtTokenUtil.generateToken(claims, Constant.JWT_SECRET, Constant.JWT_EXPIRATION);
            }
        }
        return token;
    }
}
