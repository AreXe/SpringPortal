package com.arexe.bgames.filter;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(1)
public class LogIdentificationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        MDC.put("requestId", UUID.randomUUID().toString());
        MDC.put("ip", request.getRemoteAddr());
        if (request.getRemoteUser() != null) {
            MDC.put("user", request.getRemoteUser());
        }

        filterChain.doFilter(request, response);

        MDC.clear();
    }
}
