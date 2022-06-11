package io.github.educontessi.core.address.core.util;

import io.github.educontessi.core.address.core.exception.BusinessException;

/**
 * String manipulation class
 *
 * @author Eduardo Possamai Contessi
 */
public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatName(String name) {
        StringBuilder builder = new StringBuilder();
        if (name != null) {
            try {
                name = name.toLowerCase().trim();
                name = name.replaceAll("\\s+", " ");
                String[] words = name.split(" ");

                for (String word : words) {

                    String[] wordsDmute = word.split("'"); // D'Oeste / D'Água
                    if (wordsDmute.length == 1) {
                        builder.append(checkPrepositionName(word) ? word : org.apache.commons.lang3.StringUtils.capitalize(word))
                                .append(" ");
                    } else if (wordsDmute.length == 2) {
                        builder.append(org.apache.commons.lang3.StringUtils.capitalize(wordsDmute[0])).append("'");
                        builder.append(org.apache.commons.lang3.StringUtils.capitalize(wordsDmute[1]));
                    } else {
                        throw new BusinessException("Invalid name");
                    }
                }
                return builder.toString().trim();
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
        }
        return name;
    }

    public static String formatDescription(String description) {
        if (description != null) {
            try {
                description = description.trim();
                description = description.replaceAll("\\s+", " ");
                return description;
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
        }
        return description;
    }

    public static boolean nullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }


    private static boolean checkPrepositionName(String word) {
        String caracteres = "da|das|de|do|dos|e";
        return caracteres.contains(word);
    }


}
