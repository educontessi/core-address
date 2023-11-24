package io.github.educontessi.core.address.core.usecase;

import io.github.educontessi.core.address.core.model.ZipCodeSearch;
import io.github.educontessi.core.address.core.ports.out.ZipCodeSearchAdapterPort;
import io.github.educontessi.core.address.core.validation.ZipCodeSearchValidations;
import io.github.educontessi.core.address.mock.MockSingleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for the class {@link ZipCodeSearchUseCase}
 *
 * @author Eduardo Possamai Contessi
 */
class ZipCodeSearchUseCaseTest {

    @Mock
    private ZipCodeSearchAdapterPort repository;

    @Mock
    private ZipCodeSearchValidations validations;

    @InjectMocks
    private ZipCodeSearchUseCase useCase;

    private final MockSingleton mockSingleton = MockSingleton.getInstance();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldReturnObject() {
        // Configuration
        String zipcode = "88900000";
        when(repository.search(zipcode)).thenReturn(mockSingleton.getZipCodeSearch());
        ZipCodeSearch response;

        // Execution
        response = useCase.execute(zipcode);

        // Check the results
        assertNotNull(response);
        verify(repository, times(1)).search(zipcode);
    }
}