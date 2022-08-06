package io.github.educontessi.core.address.adapters.out.feing;

import io.github.educontessi.core.address.adapters.out.feing.dto.ViaCepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Eduardo Possamai Contessi
 */
@FeignClient(value = "ViaCEPFeignClient", url = "https://viacep.com.br/ws")
public interface ViaCEPFeign {

    @GetMapping("/{zipcode}/json")
    ViaCepDto searchZipCode(@PathVariable String zipcode);

}
