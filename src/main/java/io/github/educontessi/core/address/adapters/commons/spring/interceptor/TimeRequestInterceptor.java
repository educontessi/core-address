package io.github.educontessi.core.address.adapters.commons.spring.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;

/**
 * @author Eduardo Possamai Contessi
 */
@Component
public class TimeRequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TimeRequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = Instant.now().toEpochMilli();
        logger.info("Request URL::{}:: Start Time={}", request.getRequestURL(), Instant.now());
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        logger.info("Request URL::{}:: Time Taken={}", request.getRequestURL(), (Instant.now().toEpochMilli() - startTime));
    }

}
