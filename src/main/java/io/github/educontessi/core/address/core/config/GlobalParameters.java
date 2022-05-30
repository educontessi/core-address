package io.github.educontessi.core.address.core.config;

import java.math.RoundingMode;

public class GlobalParameters {

    private GlobalParameters() {
        throw new IllegalStateException("Utility class");
    }

    public static final boolean EXCLUDE_DEFINITIVE = true;
    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;

}
