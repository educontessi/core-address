package io.github.educontessi.core.address.core.util;

/**
 * Class used for control to expand sub-objects of requests
 *
 * @author Eduardo Possamai Contessi
 */
public class ExpandUtil {

    ExpandUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final String DELIMITER_REGEX = ",";
    private static final String DOT = ".";
    private static final String ALL = "all";

    public static boolean contains(String field, String expand) {
        if (!StringUtils.nullOrEmpty(expand)) {
            return expand.contains(ALL) || expand.contains(field);
        }
        return false;
    }

    public static String extractSubExpand(String currentfield, String expand) {
        StringBuilder subExpand = new StringBuilder();

        if (StringUtils.nullOrEmpty(expand)) {
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }

        if (expand.contains(ALL)) {
            return ALL;
        }

        String[] all = expand.split(DELIMITER_REGEX);
        for (String e : all) {
            String aux = e.replace(currentfield, "").trim();
            if (aux.length() > 0) {
                subExpand.append(DELIMITER_REGEX);
                subExpand.append(getSubExpand(aux));
            }
        }
        return subExpand.toString().replaceFirst(DELIMITER_REGEX, "").trim();
    }

    protected static String getSubExpand(String aux) {
        if (aux.startsWith(DOT)) {
            return aux.replaceFirst("\\.", "");
        }
        return aux;
    }

}
