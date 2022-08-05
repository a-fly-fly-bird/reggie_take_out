package pers.tanyong.reggie.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import pers.tanyong.reggie.common.BaseContext;
import pers.tanyong.reggie.common.R;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    // 路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("拦截到请求: {}", request.getRequestURI());

        // 获取本次请求的URI
        String requestURI = request.getRequestURI();
        // 以下需要放行
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                // 静态资源可以放行
                "/backend/**",
                "/frontend/**"
        };

        // 如果不需要处理则直接放行
        Boolean check = check(urls, requestURI);
        if(check){
            log.info("本次请求不需要处理：{}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 已登陆则放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("用户已登陆：{}", requestURI);

            Long id = Thread.currentThread().getId();
            log.info("线程ID为{}", id);

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request, response);
            return;
        }

        // 如果未登陆，则返回未登陆结果，通过输出流方式向客户端页面响应数据
        log.info("用户未登陆");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    public Boolean check(String[] urls, String requestURI){
        for(String url: urls){
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
