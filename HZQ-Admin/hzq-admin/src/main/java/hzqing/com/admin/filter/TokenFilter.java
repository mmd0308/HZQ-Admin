package hzqing.com.admin.filter;

import hzqing.com.admin.constant.Constant;
import hzqing.com.admin.entity.system.User;
import hzqing.com.admin.service.system.IUserService;
import hzqing.com.common.jwt.JwtTokenUtil;
import hzqing.com.common.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(filterName = "tokenFilter", urlPatterns = "/*")
public class TokenFilter implements Filter{
    @Autowired
    private IUserService userService;

    /**
     * 不需要过滤的请求
     */
    protected static List<Pattern> patterns = new ArrayList<Pattern>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        patterns.add(Pattern.compile("/images.*")); // 图片资源不拦截
        patterns.add(Pattern.compile("/api.*"));
//        patterns.add(Pattern.compile("/api.*/show.*"));
//        patterns.add(Pattern.compile("/api/user/login"));
//        patterns.add(Pattern.compile("/api.*/dispatcher.*"));

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        System.out.println("------url ------ " + url);
        if (true) { // 不需要过滤
            // if (isInclude(url)) { // 不需要过滤
            System.out.println("放行的url：   " + url);
            chain.doFilter(request,response);
            return;
        }
        // 需要过滤的接口
        String tokens = request.getHeader(Constant.ACCESS_TOKEN_KEY);
        request.setAttribute(Constant.REPLACE_TOKEN_KEY,false); // 是否替换token  默认不替换
        if (tokens == null) {  // 表示token 不存在
            request.getRequestDispatcher("/api/dispatcher/noToken").forward(request,response);
            return;
        }

        long res = JwtTokenUtil.checkJwtExpired(tokens,Constant.JWT_SECRET);
        // 校验token是否正确
        if (res == -1) { // jwt过期,提示重新登录 token 失效
            request.getRequestDispatcher("/api/dispatcher/loseToken").forward(request,response);
            return;
        }
        // 是否更新用户的token
        if (res <= Constant.JWT_REPLACE_TOKEN){ // 校验token过期时间是否接近临界值 默认是10分钟
            request.setAttribute(Constant.REPLACE_TOKEN_KEY,true); // 返回数据的时候，更新token
        }
        String iss = (String) JwtTokenUtil.getObjectFromToken(tokens, Constant.JWT_SECRET, "iss");
        iss = AESUtil.AESDncode(Constant.AES_SECRET, iss);
        String[] split = iss.split(",");
        if (split.length == 3 ){
            request.setAttribute(Constant.USER_ID,split[0]); //存储用户id到request请求中
            String password = split[2];
            User user = userService.getById(split[0]);
            if (null != user && user.getPassword().equals(password)){ //用户名和密码正确，校验通过
                chain.doFilter(request,response);
                return;
            }
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
