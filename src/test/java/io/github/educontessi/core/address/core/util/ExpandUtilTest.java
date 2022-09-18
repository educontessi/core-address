package io.github.educontessi.core.address.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the class {@link ExpandUtil}
 *
 * @author Eduardo Possamai Contessi
 */
class ExpandUtilTest {

    @Test
    void shouldThrowIllegalStateException() {
        // Configuration
        String messageException = "Utility class";
        // Execution
        IllegalStateException exception = assertThrows(IllegalStateException.class, ExpandUtil::new);

        // Check the results
        assertNotNull(exception);

        assertEquals(messageException, exception.getMessage());
    }

    @Test
    void contains_shouldReturnTrue() {
        // Configuration
        String field = "city";
        String expand = "city.state.country";
        boolean response;

        // Execution
        response = ExpandUtil.contains(field, expand);

        // Check the results
        assertTrue(response);
    }

    @Test
    void contains_shouldReturnTrueWhenExpandAllWithFieldAll() {
        // Configuration
        String field = "all";
        String expand = "all";
        boolean response;

        // Execution
        response = ExpandUtil.contains(field, expand);

        // Check the results
        assertTrue(response);
    }

    @Test
    void contains_shouldReturnFalse() {
        // Configuration
        String field = "test";
        String expand = "capivara";
        boolean response;

        // Execution
        response = ExpandUtil.contains(field, expand);

        // Check the results
        assertFalse(response);
    }

    @Test
    void contains_shouldReturnFalseWhenExpandNull() {
        // Configuration
        String field = "city";
        boolean response;

        // Execution
        response = ExpandUtil.contains(field, null);

        // Check the results
        assertFalse(response);
    }

    @ParameterizedTest
    @CsvSource({
            "city, city.state.country, state.country",
            "city, \"\", \"\"",
            "city, all, all",
            "city, \'city.state.country, \', state.country",
    })
    void extractSubExpand_shouldExtractSubExpand(ArgumentsAccessor arguments) {
        // Configuration
        String response;

        // Execution
        response = ExpandUtil.extractSubExpand(arguments.getString(0), arguments.getString(1));

        // Check the results
        assertEquals(arguments.getString(2), response);
    }

    @Test
    void extractSubExpand_shouldExtractSubExpandWithExpandNull() {
        // Configuration
        String currentfield = "city";
        String response;

        // Execution
        response = ExpandUtil.extractSubExpand(currentfield, null);

        // Check the results
        assertEquals("", response);
    }

    @Test
    void getSubExpand_shouldGetSubExpandStartsWithDot() {
        // Configuration
        String aux = ".country";
        String expected = "country";
        String response;

        // Execution
        response = ExpandUtil.getSubExpand(aux);

        // Check the results
        assertEquals(expected, response);
    }

    @Test
    void getSubExpand_shouldGetSubExpand() {
        // Configuration
        String aux = "country";
        String expected = "country";
        String response;

        // Execution
        response = ExpandUtil.getSubExpand(aux);

        // Check the results
        assertEquals(expected, response);
    }
}