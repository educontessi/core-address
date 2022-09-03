package io.github.educontessi.core.address.adapters.out.feing.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eduardo Possamai Contessi
 */
public class HeadersInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        this.setHeaders(requestTemplate);
    }

    private void setHeaders(RequestTemplate requestTemplate) {
        getHeaders().entrySet().forEach(header ->{
            String key = header.getKey();
            String value = header.getValue();
            requestTemplate.header(key, value);
        });
    }

    protected Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put("correlationID", MDC.get("correlationId"));
        return headers;
    }
}
