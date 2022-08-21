package io.github.educontessi.core.address.adapters.out.spring;

import io.github.educontessi.core.address.adapters.out.feing.service.ViaCEPService;
import io.github.educontessi.core.address.adapters.out.persistence.service.*;
import io.github.educontessi.core.address.core.usecase.*;
import io.github.educontessi.core.address.core.validation.*;
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

    @Bean
    StreetUseCaseImpl streetUseCase(StreetService service) {
        return new StreetUseCaseImpl(service, new StreetValidations());
    }

    @Bean
    ZipCodeSearchUseCaseImpl zipCodeSearchUseCase(ViaCEPService service) {
        return new ZipCodeSearchUseCaseImpl(service, new ZipCodeSearchValidations());
    }

    @Bean
    AddressUseCaseImpl addressUseCase(AddressService service) {
        return new AddressUseCaseImpl(service, new AddressValidations());
    }
}
