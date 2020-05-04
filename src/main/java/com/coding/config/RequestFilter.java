package com.coding.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class RequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("url2:{}:{}", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        long start = System.currentTimeMillis();
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        long end = System.currentTimeMillis();
        log.info("time:{}", (end - start) + "ms");
    }
}
