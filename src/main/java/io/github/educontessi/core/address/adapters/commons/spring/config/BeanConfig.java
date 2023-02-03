package io.github.educontessi.core.address.adapters.commons.spring.config;

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
    CountryUseCase countryUseCaseImpl(CountryService service) {
        return new CountryUseCase(service, new CountryValidations());
    }

    @Bean
    StateUseCase stateUseCase(StateService service) {
        return new StateUseCase(service, new StateValidations());
    }

    @Bean
    CityUseCase cityUseCase(CityService service) {
        return new CityUseCase(service, new CityValidations());
    }

    @Bean
    NeighborhoodUseCase neighborhoodUseCase(NeighborhoodService service) {
        return new NeighborhoodUseCase(service, new NeighborhoodValidations());
    }

    @Bean
    StreetUseCase streetUseCase(StreetService service) {
        return new StreetUseCase(service, new StreetValidations());
    }

    @Bean
    ZipCodeSearchUseCase zipCodeSearchUseCase(ZipCodeSearchPortImplViaCEP service) {
        return new ZipCodeSearchUseCase(service, new ZipCodeSearchValidations());
    }

    @Bean
    AddressUseCase addressUseCase(AddressService service) {
        return new AddressUseCase(service, new AddressValidations());
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
