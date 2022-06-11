package io.github.educontessi.core.address.adapters.out.spring;

import io.github.educontessi.core.address.adapters.out.persistence.service.CountryService;
import io.github.educontessi.core.address.core.usecase.CountryUseCaseImpl;
import io.github.educontessi.core.address.core.usecase.validation.CountryValidations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    CountryUseCaseImpl countryUseCaseImpl(CountryService service) {
        return new CountryUseCaseImpl(service, new CountryValidations());
    }
}
