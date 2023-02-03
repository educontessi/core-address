package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.CityFilter;
import io.github.educontessi.core.address.core.model.City;
import io.github.educontessi.core.address.core.ports.out.CityRepositoryPort;
import io.github.educontessi.core.address.core.validation.CityValidations;
import io.github.educontessi.core.address.core.validation.Validator;
import io.github.educontessi.core.address.mock.MockSingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for the class {@link CityUseCase}
 *
 * @author Eduardo Possamai Contessi
 */
class CityUseCaseTest {

    @Mock
    private CityRepositoryPort repository;

    @Mock
    private CityValidations validations;

    @InjectMocks
    private CityUseCase useCase;

    private final MockSingleton mockSingleton = MockSingleton.getInstance();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void search_shouldReturnSearch() {
        // Configuration
        CityFilter filter = mockSingleton.getCityFilter();
        Object pageable = mockSingleton.getPageable();
        String expand = "all";

        when(repository.search(filter, pageable, expand)).thenReturn(mockSingleton.getPaginatedCity());
        Object response;

        // Execution
        response = useCase.search(filter, pageable, expand);

        // Check the results
        assertNotNull(response);
        assertTrue(response instanceof Page);
        assertEquals(1, ((Page<City>) response).getTotalElements());
        verify(repository, times(1)).search(filter, pageable, expand);
    }

    @Test
    void findById_shouldReturnObject() {
        // Configuration
        Long id = 1L;
        String expand = "all";
        when(repository.findById(id, expand)).thenReturn(mockSingleton.getOptionalCity());
        City response;

        // Execution
        response = useCase.findById(id, expand);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findById(id, expand);
    }

    @Test
    void findById_shouldReturnEntityNotFoundException() {
        // Configuration
        String expand = "all";
        Long id = 1L;
        String exceptionMessage = "There is no record with code " + id;
        when(repository.findById(id, expand)).thenReturn(Optional.empty());

        // Execution
        EntityNotFoundException response = assertThrows(EntityNotFoundException.class,
                () -> useCase.findById(id, expand));

        // Check the results
        assertNotNull(response);
        assertEquals(exceptionMessage, response.getMessage());
        verify(repository, times(1)).findById(id, expand);
    }

    @Test
    void findById_shouldReturnObjectWithOutExpand() {
        // Configuration
        CityUseCase useCaseSpy = spy(useCase);
        Long id = 1L;
        when(repository.findById(id, null)).thenReturn(mockSingleton.getOptionalCity());
        City response;

        // Execution
        response = useCaseSpy.findById(id);

        // Check the results
        assertNotNull(response);
        verify(useCaseSpy, times(1)).findById(id, null);
        verify(repository, times(1)).findById(id, null);
    }

    @Test
    void findAllByStateId_shouldReturnList() {
        // Configuration
        Long stateId = 1L;
        String expand = "all";
        when(repository.findAllByStateId(stateId, expand)).thenReturn(mockSingleton.getCityList());
        List<City> response;

        // Execution
        response = useCase.findAllByStateId(stateId, expand);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findAllByStateId(stateId, expand);
    }

    @Test
    void findByIbge_shouldReturnObject() {
        // Configuration
        Integer ibge = 123456;
        String expand = "all";
        when(repository.findByIbge(ibge, expand)).thenReturn(mockSingleton.getOptionalCity());
        City response;

        // Execution
        response = useCase.findByIbge(ibge, expand);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findByIbge(ibge, expand);
    }

    @Test
    void findByIbge_shouldReturnEntityNotFoundException() {
        // Configuration
        Integer ibge = 123456;
        String expand = "all";
        String exceptionMessage = "There is no record with IBGE " + ibge;
        when(repository.findByIbge(ibge, expand)).thenReturn(Optional.empty());

        // Execution
        EntityNotFoundException response = assertThrows(EntityNotFoundException.class,
                () -> useCase.findByIbge(ibge, expand));

        // Check the results
        assertNotNull(response);
        assertEquals(exceptionMessage, response.getMessage());
        verify(repository, times(1)).findByIbge(ibge, expand);
    }

    @Test
    void save_shouldSaveObject() {
        // Configuration
        City model = mockSingleton.getCity();
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.save(model)).thenReturn(mockSingleton.getCity());
        City response;

        // Execution
        response = useCase.save(model, validatorsOutOfCore);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).save(model);
        verify(validations, times(1)).validationsOnSave(model, validatorsOutOfCore);
    }

    @Test
    void update_shouldUpdateObject() {
        // Configuration
        CityUseCase useCaseSpy = spy(useCase);
        Long id = 1L;
        City model = mockSingleton.getCity();
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.update(any(), any())).thenReturn(mockSingleton.getCity());
        when(repository.findById(any(), any())).thenReturn(mockSingleton.getOptionalCity());
        City response;

        // Execution
        response = useCaseSpy.update(id, model, validatorsOutOfCore);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).update(any(), any());
        verify(validations, times(1)).validationsOnSave(model, validatorsOutOfCore);
        verify(useCaseSpy, times(1)).findById(any(), any());
    }

    @Test
    void delete_shouldDeleteObject() {
        // Configuration
        CityUseCase useCaseSpy = spy(useCase);
        Long id = 1L;
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.findById(any(), any())).thenReturn(mockSingleton.getOptionalCity());

        // Execution
        assertDoesNotThrow(() -> useCaseSpy.delete(id, validatorsOutOfCore));

        // Check the results
        verify(repository, times(1)).delete(any());
        verify(validations, times(1)).validationsOnDelete(validatorsOutOfCore);
        verify(useCaseSpy, times(1)).findById(any(), any());
    }

}