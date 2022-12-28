package io.github.educontessi.core.address.adapters.out.rest.feing;

import io.github.educontessi.core.address.adapters.in.rest.v1.dto.WarmUpRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Eduardo Possamai Contessi
 */
@FeignClient(value = "WarmUpControllerFeignClient", url = "http://localhost:${server.port}")
public interface WarmUpControllerFeign {

    @PostMapping("/api/core-address/v1/warmup")
    String warmup(@RequestBody WarmUpRequestDto warmUpRequestDto);
}
