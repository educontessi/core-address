package io.github.educontessi.core.address.core.util;

import io.github.educontessi.core.address.core.exception.InvalidUuidException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.UUID;

public class UUIdUtils {

    UUIdUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String uuidToString(UUID uuid) {
        return uuid.toString().replaceAll("[-]", "").toUpperCase();
    }

    public static UUID stringToUuid(String uuidString) {
        UUID uuid = null;
        validateUUID(uuidString);

        if (uuidString.length() == 32) {
            uuid = UUID.fromString(formatUUID(uuidString));
        }
        if (uuidString.length() == 36) {
            uuid = UUID.fromString(uuidString);
        }
        return uuid;
    }

    public static String formatUUID(String uuidString) {
        return uuidString.replaceAll(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5");
    }

    protected static void validateUUID(String uuidString) {
        if (Objects.isNull(uuidString)) {
            throw new InvalidUuidException("UUID cannot be null");
        }
        if (StringUtils.isBlank(uuidString)) {
            throw new InvalidUuidException("UUID cannot be empty");
        }
        if (uuidString.length() < 32 || uuidString.length() > 36) { // todo: rever logica
            throw new InvalidUuidException("Invalid UUID");
        }
    }

}
