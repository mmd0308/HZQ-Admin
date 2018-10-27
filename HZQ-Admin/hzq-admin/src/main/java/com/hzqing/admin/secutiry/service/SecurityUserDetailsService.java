package com.hzqing.admin.secutiry.service;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author hzqing
 * @date 2018/10/27 15:35
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    /**
     * 根据用户名称获取用户信息
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //UserDetails 封装用户数据的接口
        User user = new User("admin","admin",
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));
        return user;
    }

}
