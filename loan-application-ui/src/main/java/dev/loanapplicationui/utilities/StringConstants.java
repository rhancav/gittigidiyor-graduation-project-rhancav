package dev.loanapplicationui.utilities;

import org.springframework.beans.factory.annotation.Value;

public class StringConstants {
    // Chars only with minimum three and maximum thirty chars long in length
    public static final String FORENAME_REGEX = "^[\\p{L}]{3,30}$";
    public static final String SURNAME_REGEX = "^[\\p{L}]{2,30}$";
    // Min 10 and max 11 chars long, doesnt support country codes.
    public static final String PHONE_NUMBER_REGEX = "^\\s*-?[0-9]{10,11}\\s*$";
}
