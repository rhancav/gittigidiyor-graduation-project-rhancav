package dev.loanapplicationapi.utilities;

import org.springframework.util.StringUtils;

public class UtilityMethods {
    /**
     * Capitalizes the given string. The main purpose is to
     * make sure that all the user string inputs persisted with their
     * first char uppercased. So, no more "ugly" namings.
     *
     * @param s String to be capitalized.
     * @return the capitalized string.
     */
    public static String uppercaseFirstChar(String s) {
        return StringUtils.capitalize(s.toLowerCase());
    }

}
