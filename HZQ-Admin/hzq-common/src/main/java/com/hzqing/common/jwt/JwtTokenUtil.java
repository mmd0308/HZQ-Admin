package com.hzqing.common.jwt;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class JwtTokenUtil {


    public static String generateToken(Map<String, Object> claims,String secret,Integer expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate(expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 返回jwt过期时间
     * @param expiration
     * @return
     */
    private static Date generateExpirationDate(Integer expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public static String getUsernameFromToken(String token, String secret) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token,secret);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 根据token获取用户id
     * @param token
     * @param secret
     * @return
     */
    public static Object getObjectFromToken(String token,String secret,String data) {
        Claims claims = getClaimsFromToken(token, secret);
        return claims.get(data);
    }
    private static Claims getClaimsFromToken(String token,String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 检测jwt是否过期
     * @param token
     * @param secret
     * @return 如果返回-1 表示过期，否则返回距离过期的秒数
     */
    public static long checkJwtExpired(String token,String secret) {
        long expired = -1;
        try {
            Claims claims = getClaimsFromToken(token, secret);
            if (null != claims)
                expired = (claims.getExpiration().getTime() - new Date().getTime())/1000;
        } catch (ExpiredJwtException e) {
            // jwt已经过期了，不可以使用
            return expired;
        }
        //token距离过期时间为:" + expired + "秒"
        return expired;
    }


}
