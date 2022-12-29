package io.github.educontessi.core.address.adapters.out.spring.config;

import io.github.educontessi.core.address.adapters.out.persistence.jpa.service.*;
import io.github.educontessi.core.address.adapters.out.ports.impl.ZipCodeSearchPortImplViaCEP;
import io.github.educontessi.core.address.core.usecase.*;
import io.github.educontessi.core.address.core.validation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class BeanConfig {

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
    ZipCodeSearchUseCaseImpl zipCodeSearchUseCase(ZipCodeSearchPortImplViaCEP service) {
        return new ZipCodeSearchUseCaseImpl(service, new ZipCodeSearchValidations());
    }

    @Bean
    AddressUseCaseImpl addressUseCase(AddressService service) {
        return new AddressUseCaseImpl(service, new AddressValidations());
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(2048);
        loggingFilter.setIncludeHeaders(false);
        return loggingFilter;
    }


}
