package io.github.educontessi.core.address.mock;

import io.github.educontessi.core.address.adapters.out.rest.feing.dto.ViaCepDto;
import io.github.educontessi.core.address.core.enums.PropertyType;
import io.github.educontessi.core.address.core.filter.*;
import io.github.educontessi.core.address.core.model.*;
import io.github.educontessi.core.address.core.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

/**
 * @author Eduardo Possamai Contessi
 */
public class MockSingleton {

    public static MockSingleton instance;

    private MockSingleton() {
    }

    public static MockSingleton getInstance() {
        if (instance == null) {
            instance = new MockSingleton();
        }
        return instance;
    }

    public Address getAddress() {
        Address address = new Address();
        address.setId(1L);
        address.setIntegrationId(UUID.randomUUID().toString());
        address.setCountryId(1L);
        address.setStateId(1L);
        address.setCityId(1L);
        address.setNeighborhoodId(1L);
        address.setStreetId(1L);
        address.setNumber("123");
        address.setRecipientName("recipientName");
        address.setRecipientPhone("recipientPhone");
        address.setPropertyType(PropertyType.HOUSE);
        address.setComplement("complement");
        address.setComplement("proximity");
        address.setComplement("deliveryInstructions");
        return address;
    }

    public Optional<Address> getOptionalAddress() {
        return Optional.of(getAddress());
    }

    public List<Address> getAddressList() {
        return Collections.singletonList(getAddress());
    }

    public List<Validator> getValidatorsOutOfCore() {
        return new ArrayList<>();
    }

    public CityFilter getCityFilter() {
        CityFilter filter = new CityFilter();
        filter.setName("Ararangua");
        return filter;
    }

    public Pageable getPageable() {
        return Pageable.ofSize(10);
    }

    public Page<City> getPaginatedCity() {
        List<City> cityList = getCityList();
        return new PageImpl<>(cityList, getPageable(), cityList.size());
    }

    public List<City> getCityList() {
        return Collections.singletonList(getCity());
    }

    public City getCity() {
        City city = new City();
        city.setId(1L);
        city.setName("Test");
        city.setStateId(1L);
        city.setIbge(123456);
        return city;
    }

    public Optional<City> getOptionalCity() {
        return Optional.of(getCity());
    }

    public List<Country> getCountryList() {
        return Collections.singletonList(getCountry());
    }

    public Country getCountry() {
        Country country = new Country();
        country.setId(1L);
        country.setName("Test");
        country.setAcronym("TS");
        country.setBacen("0000");
        return country;
    }

    public CountryFilter getCountryFilter() {
        CountryFilter filter = new CountryFilter();
        filter.setName("Test");
        return filter;
    }

    public Page<Country> getPaginatedCountry() {
        List<Country> countryList = getCountryList();
        return new PageImpl<>(countryList, getPageable(), countryList.size());
    }

    public Optional<Country> getOptionalCountry() {
        return Optional.of(getCountry());
    }

    public NeighborhoodFilter getNeighborhoodFilter() {
        NeighborhoodFilter filter = new NeighborhoodFilter();
        filter.setName("Test");
        return filter;
    }

    public Page<Neighborhood> getPaginatedNeighborhood() {
        List<Neighborhood> neighborhoodList = getNeighborhoodList();
        return new PageImpl<>(neighborhoodList, getPageable(), neighborhoodList.size());
    }

    public List<Neighborhood> getNeighborhoodList() {
        return Collections.singletonList(getNeighborhood());
    }

    public Neighborhood getNeighborhood() {
        Neighborhood neighborhood = new Neighborhood();
        neighborhood.setId(1L);
        neighborhood.setName("Test");
        neighborhood.setCityId(1L);
        return neighborhood;
    }

    public Optional<Neighborhood> getOptionalNeighborhood() {
        return Optional.of(getNeighborhood());
    }

    public StreetFilter getStreetFilter() {
        StreetFilter filter = new StreetFilter();
        filter.setName("Test");
        return filter;
    }

    public Page<Street> getPaginatedStreet() {
        List<Street> streetList = getStreetList();
        return new PageImpl<>(streetList, getPageable(), streetList.size());
    }

    public List<Street> getStreetList() {
        return Collections.singletonList(getStreet());
    }

    public Street getStreet() {
        Street street = new Street();
        street.setId(1L);
        street.setName("Test");
        street.setCityId(1L);
        return street;
    }

    public Optional<Street> getOptionalStreet() {
        return Optional.of(getStreet());
    }

    public List<State> getStateList() {
        return Collections.singletonList(getState());
    }

    public State getState() {
        State state = new State();
        state.setId(1L);
        state.setName("Test");
        state.setUf("TS");
        state.setCountryId(1L);
        return state;
    }

    public Page<State> getPaginatedState() {
        List<State> streetList = getStateList();
        return new PageImpl<>(streetList, getPageable(), streetList.size());
    }

    public Optional<State> getOptionalState() {
        return Optional.of(getState());
    }

    public StateFilter getStateFilter() {
        StateFilter filter = new StateFilter();
        filter.setName("Test");
        return filter;
    }

    public ZipCodeSearch getZipCodeSearch() {
        ZipCodeSearch zipCodeSearch = new ZipCodeSearch();
        zipCodeSearch.setZipCode("88900000");
        zipCodeSearch.setStreet(getStreet());
        zipCodeSearch.setNeighborhood(getNeighborhood());
        zipCodeSearch.setCity(getCity());
        zipCodeSearch.setState(getState());
        return zipCodeSearch;
    }

    public ViaCepDto getViaCepDto() {
        ViaCepDto viaCepDto = new ViaCepDto();
        viaCepDto.setCep("88900000");
        viaCepDto.setLogradouro("Logradouro");
        viaCepDto.setComplemento("Complemento");
        viaCepDto.setBairro("Bairro");
        viaCepDto.setLocalidade("Localidade");
        viaCepDto.setUf("SC");
        viaCepDto.setIbge("123456");
        viaCepDto.setGia("Gia");
        viaCepDto.setDdd("Ddd");
        viaCepDto.setSiafi("Siafi");
        return viaCepDto;
    }
}
