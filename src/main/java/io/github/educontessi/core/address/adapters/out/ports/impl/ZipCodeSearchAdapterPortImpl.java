package io.github.educontessi.core.address.adapters.out.ports.impl;

import io.github.educontessi.core.address.adapters.out.rest.feing.ViaCEPFeign;
import io.github.educontessi.core.address.adapters.out.rest.feing.dto.ViaCepDto;
import io.github.educontessi.core.address.core.model.*;
import io.github.educontessi.core.address.core.ports.in.CityUseCasePort;
import io.github.educontessi.core.address.core.ports.in.NeighborhoodUseCasePort;
import io.github.educontessi.core.address.core.ports.in.StateUseCasePort;
import io.github.educontessi.core.address.core.ports.in.StreetUseCasePort;
import io.github.educontessi.core.address.core.ports.out.ZipCodeSearchAdapterPort;
import io.github.educontessi.core.address.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static io.github.educontessi.core.address.core.config.TagLogs.*;

/**
 * @author Eduardo Possamai Contessi
 */
@Service
public class ZipCodeSearchAdapterPortImpl implements ZipCodeSearchAdapterPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipCodeSearchAdapterPortImpl.class);

    private final StreetUseCasePort streetUseCasePort;
    private final NeighborhoodUseCasePort neighborhoodUseCasePort;
    private final CityUseCasePort cityUseCasePort;
    private final StateUseCasePort stateUseCasePort;
    private final ViaCEPFeign viaCEPFeign;

    public ZipCodeSearchAdapterPortImpl(StreetUseCasePort streetUseCasePort,
                                        NeighborhoodUseCasePort neighborhoodUseCasePort,
                                        CityUseCasePort cityUseCasePort,
                                        StateUseCasePort stateUseCasePort,
                                        ViaCEPFeign viaCEPFeign) {
        this.streetUseCasePort = streetUseCasePort;
        this.neighborhoodUseCasePort = neighborhoodUseCasePort;
        this.cityUseCasePort = cityUseCasePort;
        this.stateUseCasePort = stateUseCasePort;
        this.viaCEPFeign = viaCEPFeign;
    }

    @Override
    @Cacheable(value = "core-address-zipcode")
    public ZipCodeSearch search(String zipCode) {
        long start = System.currentTimeMillis();
        ZipCodeSearch zipCodeSearch = null;

        try {
            zipCode = StringUtils.removeNumberMask(zipCode);
            ViaCepDto dto = viaCEPFeign.searchZipCode(zipCode);
            if (dto != null) {
                zipCodeSearch = new ZipCodeSearch();
                zipCodeSearch.setZipCode(dto.getCep());
                zipCodeSearch.setState(getState(dto));
                zipCodeSearch.setCity(getCity(dto, zipCodeSearch.getState()));
                zipCodeSearch.setNeighborhood(getNeighborhood(dto, zipCodeSearch.getCity()));
                zipCodeSearch.setStreet(getStreet(dto, zipCodeSearch.getCity()));
            }
        } catch (Exception e) {
            LOGGER.error("{}", ZIP_CODE_SEARCH + COUNT + REQUEST + FAIL);
            throw e;
        }

        LOGGER.info("{} Total time to consult zip code {}ms", TIMER + FEIGN + VIACEP, System.currentTimeMillis() - start);
        return zipCodeSearch;
    }

    protected State getState(ViaCepDto dto) {
        return stateUseCasePort.findByUf(dto.getUf(), null);
    }

    protected City getCity(ViaCepDto dto, State state) {
        City city = cityUseCasePort.findByIbge(Integer.parseInt(dto.getIbge()), null);
        if (city == null) {
            return saveCity(dto, state);
        }
        return city;
    }

    protected City saveCity(ViaCepDto dto, State state) {
        var city = new City();
        city.setStateId(state.getId());
        city.setIbge(Integer.parseInt(dto.getIbge()));
        city.setName(dto.getLocalidade());
        return cityUseCasePort.save(city, Collections.emptyList());
    }

    protected Neighborhood getNeighborhood(ViaCepDto viaCEP, City city) {
        Neighborhood neighborhood = null;
        if (bairroIsValid(viaCEP)) {
            neighborhood = neighborhoodUseCasePort.findByNameAndCityId(viaCEP.getBairro(), city.getId());
            if (neighborhood == null) {
                neighborhood = saveNeighborhood(viaCEP, city);
            }
        }
        return neighborhood;
    }

    protected boolean bairroIsValid(ViaCepDto viaCEP) {
        return viaCEP.getBairro() != null && !StringUtils.nullOrEmpty(viaCEP.getBairro());
    }

    protected Neighborhood saveNeighborhood(ViaCepDto dto, City city) {
        var neighborhood = new Neighborhood();
        neighborhood.setCityId(city.getId());
        neighborhood.setName(dto.getBairro());
        return neighborhoodUseCasePort.save(neighborhood, Collections.emptyList());
    }

    protected Street getStreet(ViaCepDto dto, City city) {
        Street street = null;
        if (logradouroValido(dto)) {
            street = streetUseCasePort.findByNameAndCityId(dto.getLogradouro(), city.getId());
            if (street == null) {
                street = saveStreet(dto, city);
            }
        }
        return street;
    }

    protected boolean logradouroValido(ViaCepDto dto) {
        return dto.getLogradouro() != null && !StringUtils.nullOrEmpty(dto.getLogradouro());
    }

    protected Street saveStreet(ViaCepDto dto, City city) {
        var street = new Street();
        street.setCityId(city.getId());
        street.setName(dto.getLogradouro());

        return streetUseCasePort.save(street, Collections.emptyList());
    }
}
