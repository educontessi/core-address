package io.github.educontessi.core.address.adapters.out.spring;

import io.github.educontessi.core.address.adapters.out.persistence.service.CityService;
import io.github.educontessi.core.address.adapters.out.persistence.service.CountryService;
import io.github.educontessi.core.address.adapters.out.persistence.service.NeighborhoodService;
import io.github.educontessi.core.address.adapters.out.persistence.service.StateService;
import io.github.educontessi.core.address.core.usecase.CityUseCaseImpl;
import io.github.educontessi.core.address.core.usecase.CountryUseCaseImpl;
import io.github.educontessi.core.address.core.usecase.NeighborhoodUseCaseImpl;
import io.github.educontessi.core.address.core.usecase.StateUseCaseImpl;
import io.github.educontessi.core.address.core.validation.CityValidations;
import io.github.educontessi.core.address.core.validation.CountryValidations;
import io.github.educontessi.core.address.core.validation.NeighborhoodValidations;
import io.github.educontessi.core.address.core.validation.StateValidations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    CountryUseCaseImpl countryUseCaseImpl(CountryService service) {
        return new CountryUseCaseImpl(service, new CountryValidations());
    }

    @Bean
    StateUseCaseImpl stateUseCase(StateService service) {
        return new StateUseCaseImpl(service, new StateValidations());
    }

    @Bean
    CityUseCaseImpl cityUseCase(CityService service) {
        return new CityUseCaseImpl(service, new CityValidations());
    }

    @Bean
    NeighborhoodUseCaseImpl neighborhoodUseCase(NeighborhoodService service) {
        return new NeighborhoodUseCaseImpl(service, new NeighborhoodValidations());
    }
}
