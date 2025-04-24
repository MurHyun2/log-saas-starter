
package com.oursaas.autolog;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

public class AutoLogFilter extends OncePerRequestFilter {
    private final LogSender sender = new LogSender();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long duration = System.currentTimeMillis() - start;

        Map<String, Object> log = Map.of(
                "path", request.getRequestURI(),
                "method", request.getMethod(),
                "status", response.getStatus(),
                "duration_ms", duration,
                "timestamp", Instant.now().toString()
        );

        sender.send(log);
    }
}
