package io.github.educontessi.core.address.adapters.out.event.spring;

import io.github.educontessi.core.address.adapters.in.rest.v1.dto.WarmUpRequestDto;
import io.github.educontessi.core.address.adapters.out.rest.feing.WarmUpControllerFeign;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for the class {@link WarmMeUpListenerAsync}
 *
 * @author Eduardo Possamai Contessi
 */
class WarmMeUpListenerAsyncTest {

    @Mock
    private WarmUpControllerFeign feign;

    @InjectMocks
    private WarmMeUpListenerAsync warmMeUp;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void onApplicationEvent_shouldSendWarmUpRestRequest() {
        // Configuration
        WarmMeUpListenerAsync warmMeUpSpy = spy(warmMeUp);
        ApplicationReadyEvent event = mock(ApplicationReadyEvent.class);

        // Execution
        assertDoesNotThrow(() -> warmMeUpSpy.onApplicationEvent(event));

        // Check the results
        verify(warmMeUpSpy, times(1)).sendWarmUpRestRequest();
    }

    @Test
    void sendWarmUpRestRequest_shouldSendWarmUpRestRequest() {
        // Configuration
        WarmMeUpListenerAsync warmMeUpSpy = spy(warmMeUp);

        // Execution
        assertDoesNotThrow(warmMeUpSpy::sendWarmUpRestRequest);

        // Check the results
        verify(warmMeUpSpy, times(1)).createSampleMessage();
        verify(feign, times(1)).warmup(any());
    }

    @Test
    void createSampleMessage_shouldCreateSampleMessage() {
        // Configuration
        WarmUpRequestDto response;

        // Execution
        response = warmMeUp.createSampleMessage();

        // Check the results
        assertNotNull(response);
    }
}