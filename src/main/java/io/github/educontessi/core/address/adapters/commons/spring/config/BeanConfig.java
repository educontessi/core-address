package io.github.educontessi.core.address.adapters.commons.spring.config;

import io.github.educontessi.core.address.adapters.out.ports.impl.*;
import io.github.educontessi.core.address.core.usecase.*;
import io.github.educontessi.core.address.core.validation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class BeanConfig {

    @Bean
    CountryUseCase countryUseCaseImpl(CountryAdapterPortImpl service) {
        return new CountryUseCase(service, new CountryValidations());
    }

    @Bean
    StateUseCase stateUseCase(StateAdapterPortImpl service) {
        return new StateUseCase(service, new StateValidations());
    }

    @Bean
    CityUseCase cityUseCase(CityAdapterPortImpl service) {
        return new CityUseCase(service, new CityValidations());
    }

    @Bean
    NeighborhoodUseCase neighborhoodUseCase(NeighborhoodAdapterPortImpl service) {
        return new NeighborhoodUseCase(service, new NeighborhoodValidations());
    }

    @Bean
    StreetUseCase streetUseCase(StreetAdapterPortImpl service) {
        return new StreetUseCase(service, new StreetValidations());
    }

    @Bean
    ZipCodeSearchUseCase zipCodeSearchUseCase(ZipCodeSearchAdapterPortImpl service) {
        return new ZipCodeSearchUseCase(service, new ZipCodeSearchValidations());
    }

    @Bean
    AddressUseCase addressUseCase(AddressAdapterPortImpl service) {
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
