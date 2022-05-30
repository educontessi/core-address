package io.github.educontessi.core.address.core.util;

import io.github.educontessi.core.address.core.enums.MaskType;
import io.github.educontessi.core.address.core.enums.PersonType;
import io.github.educontessi.core.address.core.exception.BusinessException;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

/**
 * String manipulation class
 *
 * @author Eduardo Possamai Contessi
 */
public class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String addMask(MaskType maskType, Object value) {
        return addMask(maskType.getMask(), value);
    }

    public static String addMask(PersonType personType, Object value) {
        return addMask(MaskType.getPersonMaskType(personType), value);
    }

    private static String addMask(String maskType, Object value) {
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(maskType);
            mask.setValueContainsLiteralCharacters(false);
            value = value == null ? "" : value;
            return mask.valueToString(value);
        } catch (ParseException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public static String removeNumberMask(String value) {
        if (value == null) {
            return value;
        }
        return value.replaceAll("[^0123456789]", "");
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
                        throw new BusinessException("Nome Inválido");
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
