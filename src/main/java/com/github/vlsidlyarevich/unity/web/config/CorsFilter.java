package com.github.vlsidlyarevich.unity.web.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    private static final String CORS_ALLOWED_METHODS = "POST, GET, PUT, OPTIONS, DELETE, PATCH";
    private static final int CORS_MAX_AGE = 3600;
    private static final String CORS_ALLOW_HEADERS = "x-auth-token, Content-Type";
    private static final String CORS_EXPOSE_HEADERS = "x-auth-token, Content-Type";

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", CORS_ALLOWED_METHODS);
        response.setHeader("Access-Control-Max-Age", String.valueOf(CORS_MAX_AGE));
        response.setHeader("Access-Control-Allow-Headers", CORS_ALLOW_HEADERS);
        response.setHeader("Access-Control-Expose-Headers", CORS_EXPOSE_HEADERS);

        final HttpServletRequest request = (HttpServletRequest) req;

        if ("OPTIONS".equals(request.getMethod())) {
            try {
                response.getWriter().print("OK");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
