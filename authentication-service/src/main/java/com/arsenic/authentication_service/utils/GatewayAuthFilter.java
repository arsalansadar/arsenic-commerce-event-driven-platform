package com.arsenic.authentication_service.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class GatewayAuthFilter extends OncePerRequestFilter {
    private static final String SECRET_KEY = "my-secure-key-123";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String headerValue = request.getHeader("X-Gateway-Auth");

        if (headerValue == null || !headerValue.equals(SECRET_KEY)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Access Denied: Must go through API Gateway");
            return;
        }

        filterChain.doFilter(request, response);

    }
}
