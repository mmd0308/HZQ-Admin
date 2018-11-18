package com.hzqing.admin.config;

import com.hzqing.admin.filter.JWTAuthenticationFilter;
import com.hzqing.admin.secutiry.CustomAuthenticationFailure;
import com.hzqing.admin.secutiry.CustomAuthenticationSuccess;
import com.hzqing.admin.secutiry.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 配置文件
 * @author hzqing
 * @date 2018/10/27 14:37
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailService;

    @Autowired
    private CustomAuthenticationSuccess customAuthenticationSuccess;

    @Autowired
    private CustomAuthenticationFailure customAuthenticationFailure;

    @Autowired
    private  PasswordEncoder passwordEncoder;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      // auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).roles("admin");
      // auth.inMemoryAuthentication().withUser("hzqing").password(passwordEncoder().encode("hzqing")).roles("hzqing");
       auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //关闭跨站请求防护
            .headers().frameOptions().disable() //允许网页iframe
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers("/hzq/login_error").permitAll()
            .antMatchers("/hzq/tools/generator/generatorCode").permitAll()
            .antMatchers("/druid/**").permitAll() // 放行所有druid资源
            .antMatchers("/swagger-*/**","/webjars/**","/v2/**").permitAll()  // 放行所有的swagger-ui资源
            .antMatchers("/**").authenticated()
            .and()
            //前后端分离采用JWT 不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin()
            .loginPage("/hzq/login_error")
            .loginProcessingUrl("/hzq/login")
            .successHandler(customAuthenticationSuccess)
            .failureHandler(customAuthenticationFailure)
            .and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager(),userDetailService))
        ;
    }





    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
