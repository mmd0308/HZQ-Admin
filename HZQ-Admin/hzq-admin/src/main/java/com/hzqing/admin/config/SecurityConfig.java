package com.hzqing.admin.config;

import com.hzqing.admin.filter.JWTAuthenticationFilter;
import com.hzqing.admin.filter.JWTLoginFilter;
import com.hzqing.admin.secutiry.MyAuehenticationSuccess;
import com.hzqing.admin.secutiry.MyAuthenticationFail;
import com.hzqing.admin.secutiry.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 配置文件
 * @author hzqing
 * @date 2018/10/27 14:37
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailService;

    @Autowired
    private MyAuehenticationSuccess myAuehenticationSuccess;

    @Autowired
    private MyAuthenticationFail myAuthenticationFail;

    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      // auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).roles("admin");
//        auth.inMemoryAuthentication().withUser("hzqing").password(passwordEncoder().encode("hzqing")).roles("hzqing");
       auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS,"/**").permitAll() // 避免cors options请求方式全部放行
            .antMatchers("/**").authenticated() // 拦截所有请求
            .and()
            .headers().frameOptions().disable()
            .and()
            .addFilterBefore(new JWTLoginFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .addFilter(new JWTAuthenticationFilter(authenticationManager(),userDetailService))
            .formLogin()//.loginPage("/hzq/login_p").loginProcessingUrl("/hzq/login").permitAll().usernameParameter("username").passwordParameter("password")
            .failureHandler(myAuthenticationFail)
            .successHandler(myAuehenticationSuccess)
        ;
    }





    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
