package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.exception.EntityNotFoundException;
import io.github.educontessi.core.address.core.model.Address;
import io.github.educontessi.core.address.core.ports.out.AddressRepositoryPort;
import io.github.educontessi.core.address.core.validation.AddressValidations;
import io.github.educontessi.core.address.core.validation.Validator;
import io.github.educontessi.core.address.mock.MockSingleton;
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
 * Unit Tests for the class {@link AddressUseCase}
 *
 * @author Eduardo Possamai Contessi
 */
class AddressUseCaseTest {

    @Mock
    private AddressRepositoryPort repository;

    @Mock
    private AddressValidations validations;

    @InjectMocks
    private AddressUseCase useCase;

    private final MockSingleton mockSingleton = MockSingleton.getInstance();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllByIntegrationId_shouldReturnList() {
        // Configuration
        String integrationId = UUID.randomUUID().toString();
        when(repository.findAllByIntegrationId(integrationId)).thenReturn(mockSingleton.getAddressList());
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
        when(repository.findById(id, integrationId)).thenReturn(mockSingleton.getOptionalAddress());
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
        when(repository.findDefaultAddress(integrationId)).thenReturn(mockSingleton.getOptionalAddress());
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
        Address model = mockSingleton.getAddress();
        List<Validator > validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.save(model)).thenReturn(mockSingleton.getAddress());
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
        AddressUseCase useCaseSpy = spy(useCase);
        Long id = 1L;
        Address model = mockSingleton.getAddress();
        List<Validator > validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.update(any(), any())).thenReturn(mockSingleton.getAddress());
        when(repository.findById(any(), any())).thenReturn(mockSingleton.getOptionalAddress());
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
        AddressUseCase useCaseSpy = spy(useCase);
        Long id = 1L;
        String integrationId = UUID.randomUUID().toString();
        List<Validator > validatorsOutOfCore = mockSingleton.getValidatorsOutOfCore();
        when(repository.findById(any(), any())).thenReturn(mockSingleton.getOptionalAddress());

        // Execution
        assertDoesNotThrow(() -> useCaseSpy.delete(id, integrationId, validatorsOutOfCore));

        // Check the results
        verify(repository, times(1)).delete(any());
        verify(validations, times(1)).validationsOnDelete(validatorsOutOfCore);
        verify(useCaseSpy, times(1)).findById(any(), any());
    }
}