package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.CountryFilter;
import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.core.ports.out.CountryRepositoryPort;
import io.github.educontessi.core.address.core.validation.CountryValidations;
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
 * Unit Tests for the class {@link CountryUseCaseImpl}
 *
 * @author Eduardo Possamai Contessi
 */
class CountryUseCaseImplTest {

    @Mock
    private CountryRepositoryPort repository;

    @Mock
    private CountryValidations validations;

    @InjectMocks
    private CountryUseCaseImpl useCase;

    private final MockSingleton mockSingleton = MockSingleton.getInstance();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnList() {
        // Configuration
        when(repository.findAll()).thenReturn(mockSingleton.getCountryList());
        List<Country> response;

        // Execution
        response = useCase.findAll();

        // Check the results
        assertNotNull(response);
        assertEquals(1, response.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void search_shouldReturnSearch() {
        // Configuration
        CountryFilter filter = mockSingleton.getCountryFilter();
        Object pageable = mockSingleton.getPageable();

        when(repository.search(filter, pageable)).thenReturn(mockSingleton.getPaginatedCountry());
        Object response;

        // Execution
        response = useCase.search(filter, pageable);

        // Check the results
        assertNotNull(response);
        assertTrue(response instanceof Page);
        assertEquals(1, ((Page<Country>) response).getTotalElements());
        verify(repository, times(1)).search(filter, pageable);
    }

    @Test
    void findById_shouldReturnObject() {
        // Configuration
        Long id = 1L;
        when(repository.findById(id)).thenReturn(mockSingleton.getOptionalCountry());
        Country response;

        // Execution
        response = useCase.findById(id);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void findById_shouldReturnEntityNotFoundException() {
        // Configuration
        Long id = 1L;
        String exceptionMessage = "There is no record with code " + id;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Execution
        EntityNotFoundException response = assertThrows(EntityNotFoundException.class,
                () -> useCase.findById(id));

        // Check the results
        assertNotNull(response);
        assertEquals(exceptionMessage, response.getMessage());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void save_shouldSaveObject() {
        // Configuration
        Country model = mockSingleton.getCountry();
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.save(model)).thenReturn(mockSingleton.getCountry());
        Country response;

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
        CountryUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        Country model = mockSingleton.getCountry();
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.update(any(), any())).thenReturn(mockSingleton.getCountry());
        when(repository.findById(any())).thenReturn(mockSingleton.getOptionalCountry());
        Country response;

        // Execution
        response = useCaseSpy.update(id, model, validatorsOutOfCore);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).update(any(), any());
        verify(validations, times(1)).validationsOnSave(model, validatorsOutOfCore);
        verify(useCaseSpy, times(1)).findById(any());
    }

    @Test
    void delete_shouldDeleteObject() {
        // Configuration
        CountryUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        List<Validator> validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.findById(any())).thenReturn(mockSingleton.getOptionalCountry());

        // Execution
        assertDoesNotThrow(() -> useCaseSpy.delete(id, validatorsOutOfCore));

        // Check the results
        verify(repository, times(1)).delete(any());
        verify(validations, times(1)).validationsOnDelete(validatorsOutOfCore);
        verify(useCaseSpy, times(1)).findById(any());
    }
}