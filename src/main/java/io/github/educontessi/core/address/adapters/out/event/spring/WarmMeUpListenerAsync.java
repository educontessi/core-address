package io.github.educontessi.core.address.adapters.out.event.spring;

import io.github.educontessi.core.address.adapters.in.rest.v1.dto.WarmUpRequestDto;
import io.github.educontessi.core.address.adapters.out.rest.feing.WarmUpControllerFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Eduardo Possamai Contessi
 */
@Component
@Async
public class WarmMeUpListenerAsync {

    private final Logger logger = LoggerFactory.getLogger(WarmMeUpListenerAsync.class);

    private final WarmUpControllerFeign feign;

    public WarmMeUpListenerAsync(WarmUpControllerFeign feign) {
        this.feign = feign;
    }

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Starting WarmMeUp {}", event.getTimestamp());
        sendWarmUpRestRequest();
    }

    protected void sendWarmUpRestRequest() {
        logger.info("Sending REST request to force initialization of Jackson...");
        final String response = feign.warmup(createSampleMessage());
        logger.info("...done, response received: {}", response);
    }

    protected WarmUpRequestDto createSampleMessage() {
        WarmUpRequestDto warmUpRequestDto = new WarmUpRequestDto();
        warmUpRequestDto.setWarmUpString("warm me up");
        warmUpRequestDto.setWarmUpNumber(15);
        warmUpRequestDto.setWarmUpBigDecimal(BigDecimal.TEN);
        warmUpRequestDto.setWarmUpEnumDto(WarmUpRequestDto.WarmUpEnumDto.WARM);
        return warmUpRequestDto;
    }
}
