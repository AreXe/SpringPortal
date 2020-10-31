package com.arexe.bgames.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Component
@Order(2)
@Slf4j(topic = "bgames.logger")
public class RequestLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Instant start = Instant.now();
        log.info("[REQUEST] {} {}", request.getMethod(), request.getRequestURL());

        filterChain.doFilter(request, response);

        long duration = Duration.between(start, Instant.now()).toMillis();
        log.info("[RESPONSE] STATUS {}, duration: {} ms", response.getStatus(), duration);
    }
}
