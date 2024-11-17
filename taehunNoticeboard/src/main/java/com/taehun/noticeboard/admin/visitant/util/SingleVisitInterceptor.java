package com.taehun.noticeboard.admin.visitant.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class SingleVisitInterceptor extends GenericFilterBean {
    private final RedisTemplate<String, String> redisTemplate;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String userIp = request.getRemoteAddr();
        String userAgent = ((HttpServletRequest)request).getHeader("User-Agent");
        String today = LocalDate.now().toString();
        String key = userIp + "_" + today;
        ValueOperations valueOperations = this.redisTemplate.opsForValue();
        if (!valueOperations.getOperations().hasKey(key)) {
            valueOperations.set(key, userAgent);
        }

        chain.doFilter(request, response);
    }

    public SingleVisitInterceptor(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
