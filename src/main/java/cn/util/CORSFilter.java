package cn.util;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Des
 * Created with IntelliJ IDEA
 * Created by xuyf
 * Date 2018/3/20
 * Time 15:35
 */
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // 跨域
        String origin = httpRequest.getHeader("Origin");
        if (origin == null) {
            httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        } else {
            httpResponse.addHeader("Access-Control-Allow-Origin", origin);
        }
        httpResponse.addHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Content-Type, Accept,X-Cookie");
        httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS,DELETE");
        if (httpRequest.getMethod().equals("OPTIONS")) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
