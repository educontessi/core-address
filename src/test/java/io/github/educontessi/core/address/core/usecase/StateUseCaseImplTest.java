package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.filter.StateFilter;
import io.github.educontessi.core.address.core.model.State;
import io.github.educontessi.core.address.core.ports.out.StateRepositoryPort;
import io.github.educontessi.core.address.core.validation.StateValidations;
import io.github.educontessi.core.address.core.validation.Validator;
import io.github.educontessi.core.address.mock.MockFactory;
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
 * Unit Tests for the class {@link StateUseCaseImpl}
 *
 * @author Eduardo Possamai Contessi
 */
class StateUseCaseImplTest {

    @Mock
    private StateRepositoryPort repository;

    @Mock
    private StateValidations validations;

    @InjectMocks
    private StateUseCaseImpl useCase;

    private final MockFactory mockFactory = new MockFactory();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnList() {
        // Configuration
        String expand = "all";
        when(repository.findAll(expand)).thenReturn(mockFactory.getStateList());
        List<State> response;

        // Execution
        response = useCase.findAll(expand);

        // Check the results
        assertNotNull(response);
        assertEquals(1, response.size());
        verify(repository, times(1)).findAll(expand);
    }

    @Test
    void search_shouldReturnSearch() {
        // Configuration
        StateFilter filter = mockFactory.getStateFilter();
        Object pageable = mockFactory.getPageable();
        String expand = "all";

        when(repository.search(filter, pageable, expand)).thenReturn(mockFactory.getPaginatedState());
        Object response;

        // Execution
        response = useCase.search(filter, pageable, expand);

        // Check the results
        assertNotNull(response);
        assertTrue(response instanceof Page);
        assertEquals(1, ((Page<State>) response).getTotalElements());
        verify(repository, times(1)).search(filter, pageable, expand);
    }

    @Test
    void findById_shouldReturnObject() {
        // Configuration
        Long id = 1L;
        String expand = "all";
        when(repository.findById(id, expand)).thenReturn(mockFactory.getOptionalState());
        State response;

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
        StateUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        when(repository.findById(id, null)).thenReturn(mockFactory.getOptionalState());
        State response;

        // Execution
        response = useCaseSpy.findById(id);

        // Check the results
        assertNotNull(response);
        verify(useCaseSpy, times(1)).findById(id, null);
        verify(repository, times(1)).findById(id, null);
    }

    @Test
    void findAllByCountryId_shouldReturnList() {
        // Configuration
        Long countryId = 1L;
        String expand = "all";
        when(repository.findAllByCountryId(countryId, expand)).thenReturn(mockFactory.getStateList());
        List<State> response;

        // Execution
        response = useCase.findAllByCountryId(countryId, expand);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findAllByCountryId(countryId, expand);
    }

    @Test
    void findByUf_shouldReturnList() {
        // Configuration
        String uf = "TS";
        String expand = "all";
        when(repository.findByUf(uf, expand)).thenReturn(mockFactory.getOptionalState());
        State response;

        // Execution
        response = useCase.findByUf(uf, expand);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findByUf(uf, expand);
    }

    @Test
    void findByUf_shouldReturnEntityNotFoundException() {
        // Configuration
        String uf = "TS";
        String expand = "all";
        String exceptionMessage = "There is no record with UF " + uf;
        when(repository.findByUf(uf, expand)).thenReturn(Optional.empty());

        // Execution
        EntityNotFoundException response = assertThrows(EntityNotFoundException.class,
                () -> useCase.findByUf(uf, expand));

        // Check the results
        assertNotNull(response);
        assertEquals(exceptionMessage, response.getMessage());
        verify(repository, times(1)).findByUf(uf, expand);
    }

    @Test
    void save_shouldSaveObject() {
        // Configuration
        State model = mockFactory.getState();
        List<Validator> validatorsOutOfCore = mockFactory.getValidatorsOutOfCore();
        when(repository.save(model)).thenReturn(mockFactory.getState());
        State response;

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
        StateUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        State model = mockFactory.getState();
        List<Validator> validatorsOutOfCore = mockFactory.getValidatorsOutOfCore();
        when(repository.update(any(), any())).thenReturn(mockFactory.getState());
        when(repository.findById(any(), any())).thenReturn(mockFactory.getOptionalState());
        State response;

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
        StateUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        List<Validator> validatorsOutOfCore = mockFactory.getValidatorsOutOfCore();
        when(repository.findById(any(), any())).thenReturn(mockFactory.getOptionalState());

        // Execution
        assertDoesNotThrow(() -> useCaseSpy.delete(id, validatorsOutOfCore));

        // Check the results
        verify(repository, times(1)).delete(any());
        verify(validations, times(1)).validationsOnDelete(validatorsOutOfCore);
        verify(useCaseSpy, times(1)).findById(any(), any());
    }

}