package io.github.educontessi.core.address.adapters.out.ports.impl;

import io.github.educontessi.core.address.adapters.out.rest.feing.ViaCEPFeign;
import io.github.educontessi.core.address.adapters.out.rest.feing.dto.ViaCepDto;
import io.github.educontessi.core.address.core.model.ZipCodeSearch;
import io.github.educontessi.core.address.core.ports.in.CityUseCasePort;
import io.github.educontessi.core.address.core.ports.in.NeighborhoodUseCasePort;
import io.github.educontessi.core.address.core.ports.in.StateUseCasePort;
import io.github.educontessi.core.address.core.ports.in.StreetUseCasePort;
import io.github.educontessi.core.address.mock.MockSingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for the class {@link ZipCodeSearchAdapterPortImpl}
 *
 * @author Eduardo Possamai Contessi
 */
class ZipCodeSearchAdapterPortImplTest {

    @Mock
    private StreetUseCasePort streetUseCasePort;

    @Mock
    private NeighborhoodUseCasePort neighborhoodUseCasePort;

    @Mock
    private CityUseCasePort cityUseCasePort;

    @Mock
    private StateUseCasePort stateUseCasePort;

    @Mock
    private ViaCEPFeign viaCEPFeign;

    @InjectMocks
    private ZipCodeSearchAdapterPortImpl zipCodeSearchAdapterPortImpl;

    private final MockSingleton mockSingleton = MockSingleton.getInstance();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void search_shouldReturnObject() {
        // Configuration
        ZipCodeSearchAdapterPortImpl zipCodeSearchAdapterPortImplSpy = spy(zipCodeSearchAdapterPortImpl);
        ViaCepDto viaCepDto = mockSingleton.getViaCepDto();
        String zipCode = "88900000";
        when(viaCEPFeign.searchZipCode(any())).thenReturn(viaCepDto);
        when(stateUseCasePort.findByUf(any(), any())).thenReturn(mockSingleton.getState()).getMock();
        when(cityUseCasePort.findByIbge(any(), any())).thenReturn(mockSingleton.getCity()).getMock();
        when(neighborhoodUseCasePort.findByNameAndCityId(any(), any())).thenReturn(mockSingleton.getNeighborhood()).getMock();
        when(streetUseCasePort.findByNameAndCityId(any(), any())).thenReturn(mockSingleton.getStreet()).getMock();

        ZipCodeSearch response;

        // Execution
        response = zipCodeSearchAdapterPortImplSpy.search(zipCode);

        // Check the results
        assertNotNull(response);
        assertNotNull(response.getState());
        assertNotNull(response.getCity());
        assertNotNull(response.getNeighborhood());
        assertNotNull(response.getStreet());
        verify(zipCodeSearchAdapterPortImplSpy, times(1)).getState(any());
        verify(zipCodeSearchAdapterPortImplSpy, times(1)).getCity(any(), any());
        verify(zipCodeSearchAdapterPortImplSpy, times(1)).getNeighborhood(any(), any());
        verify(zipCodeSearchAdapterPortImplSpy, times(1)).getStreet(any(), any());
    }

}