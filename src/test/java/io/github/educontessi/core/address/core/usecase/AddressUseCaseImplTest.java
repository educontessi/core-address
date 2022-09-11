package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.model.Address;
import io.github.educontessi.core.address.core.ports.out.AddressRepositoryPort;
import io.github.educontessi.core.address.core.validation.AddressValidations;
import io.github.educontessi.core.address.core.validation.Validator;
import io.github.educontessi.core.address.mock.MockFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for the class {@link AddressUseCaseImpl}
 *
 * @author Eduardo Possamai Contessi
 */
class AddressUseCaseImplTest {

    @Mock
    private AddressRepositoryPort repository;

    @Mock
    private AddressValidations validations;

    @InjectMocks
    private AddressUseCaseImpl useCase;

    private final MockFactory mockFactory = new MockFactory();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllByIntegrationId_shouldReturnList() {
        // Configuration
        String integrationId = UUID.randomUUID().toString();
        when(repository.findAllByIntegrationId(integrationId)).thenReturn(mockFactory.getAddressList());
        List<Address> response;

        // Execution
        response = useCase.findAllByIntegrationId(integrationId);

        // Check the results
        assertNotNull(response);
        assertEquals(1, response.size());
        verify(repository, times(1)).findAllByIntegrationId(integrationId);
    }

    @Test
    void findById_shouldReturnObject() {
        // Configuration
        String integrationId = UUID.randomUUID().toString();
        Long id = 1L;
        when(repository.findById(id, integrationId)).thenReturn(mockFactory.getOptionalAddress());
        Address response;

        // Execution
        response = useCase.findById(id, integrationId);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findById(id, integrationId);
    }

    @Test
    void findById_shouldReturnEntityNotFoundException() {
        // Configuration
        String integrationId = UUID.randomUUID().toString();
        Long id = 1L;
        String exceptionMessage = "There is no record with code " + id;
        when(repository.findById(id, integrationId)).thenReturn(Optional.empty());

        // Execution
        EntityNotFoundException response = assertThrows(EntityNotFoundException.class,
                () -> useCase.findById(id, integrationId));

        // Check the results
        assertNotNull(response);
        assertEquals(exceptionMessage, response.getMessage());
        verify(repository, times(1)).findById(id, integrationId);
    }

    @Test
    void findDefaultAddress_shouldReturnObject() {
        // Configuration
        String integrationId = UUID.randomUUID().toString();
        when(repository.findDefaultAddress(integrationId)).thenReturn(mockFactory.getOptionalAddress());
        Address response;

        // Execution
        response = useCase.findDefaultAddress(integrationId);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).findDefaultAddress(integrationId);
    }

    @Test
    void findDefaultAddress_shouldReturnEntityNotFoundException() {
        // Configuration
        String integrationId = UUID.randomUUID().toString();
        String exceptionMessage = "There is no default address for the integrationId " + integrationId;
        when(repository.findDefaultAddress(integrationId)).thenReturn(Optional.empty());

        // Execution
        EntityNotFoundException response = assertThrows(EntityNotFoundException.class,
                () -> useCase.findDefaultAddress(integrationId));

        // Check the results
        assertNotNull(response);
        assertEquals(exceptionMessage, response.getMessage());
        verify(repository, times(1)).findDefaultAddress(integrationId);
    }

    @Test
    void save_shouldSaveObject() {
        // Configuration
        Address model = mockFactory.getAddress();
        List<Validator > validatorsOutOfCore = mockFactory.getValidatorsOutOfCore();
        when(repository.save(model)).thenReturn(mockFactory.getAddress());
        Address response;

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
        AddressUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        Address model = mockFactory.getAddress();
        List<Validator > validatorsOutOfCore = mockFactory.getValidatorsOutOfCore();
        when(repository.update(any(), any())).thenReturn(mockFactory.getAddress());
        when(repository.findById(any(), any())).thenReturn(mockFactory.getOptionalAddress());
        Address response;

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
        AddressUseCaseImpl useCaseSpy = spy(useCase);
        Long id = 1L;
        String integrationId = UUID.randomUUID().toString();
        List<Validator > validatorsOutOfCore = mockFactory.getValidatorsOutOfCore();
        when(repository.findById(any(), any())).thenReturn(mockFactory.getOptionalAddress());

        // Execution
        assertDoesNotThrow(() -> useCaseSpy.delete(id, integrationId, validatorsOutOfCore));

        // Check the results
        verify(repository, times(1)).delete(any());
        verify(validations, times(1)).validationsOnDelete(validatorsOutOfCore);
        verify(useCaseSpy, times(1)).findById(any(), any());
    }
}