package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.StreetFilter;
import io.github.educontessi.core.address.core.model.Street;
import io.github.educontessi.core.address.core.ports.out.StreetRepositoryPort;
import io.github.educontessi.core.address.core.validation.StreetValidations;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for the class {@link StreetUseCaseImpl}
 *
 * @author Eduardo Possamai Contessi
 */
class StreetUseCaseImplTest {

    @Mock
    private StreetRepositoryPort repository;

    @Mock
    private StreetValidations validations;

    @InjectMocks
    private StreetUseCaseImpl useCase;

    private final MockSingleton mockSingleton = MockSingleton.getInstance();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void search_shouldReturnSearch() {
        // Configuration
        StreetFilter filter = mockSingleton.getStreetFilter();
        Object pageable = mockSingleton.getPageable();
        String expand = "all";

        when(repository.search(filter, pageable, expand)).thenReturn(mockSingleton.getPaginatedStreet());
        Object response;

        // Execution
        response = useCase.search(filter, pageable, expand);

        // Check the results
        assertNotNull(response);
        assertTrue(response instanceof Page);
        assertEquals(1, ((Page<Street>) response).getTotalElements());
        verify(repository, times(1)).search(filter, pageable, expand);
    }

    @Test
    void findById_shouldReturnObject() {
        // Configuration
        Long id = 1L;
        String expand = "all";
        when(repository.findById(id, expand)).thenReturn(mockSingleton.getOptionalStreet());
        Street response;

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
        StreetUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        when(repository.findById(id, null)).thenReturn(mockSingleton.getOptionalStreet());
        Street response;

        // Execution
        response = useCaseSpy.findById(id);

        // Check the results
        assertNotNull(response);
        verify(useCaseSpy, times(1)).findById(id, null);
        verify(repository, times(1)).findById(id, null);
    }

    @Test
    void findAllByCityId_shouldReturnList() {
        // Configuration
        Long stateId = 1L;
        String expand = "all";
        when(repository.findAllByCityId(stateId, expand)).thenReturn(mockSingleton.getStreetList());
        List<Street> response;

        // Execution
        response = useCase.findAllByCityId(stateId, expand);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findAllByCityId(stateId, expand);
    }


    @Test
    void save_shouldSaveObject() {
        // Configuration
        Street model = mockSingleton.getStreet();
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.save(model)).thenReturn(mockSingleton.getStreet());
        Street response;

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
        StreetUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        Street model = mockSingleton.getStreet();
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.update(any(), any())).thenReturn(mockSingleton.getStreet());
        when(repository.findById(any(), any())).thenReturn(mockSingleton.getOptionalStreet());
        Street response;

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
        StreetUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.findById(any(), any())).thenReturn(mockSingleton.getOptionalStreet());

        // Execution
        assertDoesNotThrow(() -> useCaseSpy.delete(id, validatorsOutOfCore));

        // Check the results
        verify(repository, times(1)).delete(any());
        verify(validations, times(1)).validationsOnDelete(validatorsOutOfCore);
        verify(useCaseSpy, times(1)).findById(any(), any());
    }

    @Test
    void findByNameAndCityId_shouldReturnObject() {
        // Configuration
        String name = "Test";
        Long cityId = 1L;
        when(repository.findByNameAndCityId(name, cityId)).thenReturn(mockSingleton.getOptionalStreet());
        Street response;

        // Execution
        response = useCase.findByNameAndCityId(name, cityId);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findByNameAndCityId(name, cityId);
    }

    @Test
    void findByNameAndCityId_shouldReturnNull() {
        // Configuration
        String name = "Test";
        Long cityId = 1L;
        when(repository.findByNameAndCityId(name, cityId)).thenReturn(Optional.empty());
        Street response;

        // Execution
        response = useCase.findByNameAndCityId(name, cityId);

        // Check the results
        assertNull(response);
        verify(repository, times(1)).findByNameAndCityId(name, cityId);
    }

}