package io.github.educontessi.core.address.adapters.commons.spring.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Eduardo Possamai Contessi
 */
@Component
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(final    InterceptorRegistry registry) {
        registry.addInterceptor(new CorrelationIdInterceptor());
        registry.addInterceptor(new TimeRequestInterceptor());
    }
}
