package dev.loanapplicationservice.utilities;

import org.springframework.beans.factory.annotation.Value;

public class StringConstants {
    // Chars only with minimum three and maximum thirty chars long in length
    public static final String CHAR_ONLY_REGEX_MIN_MAX = "^[\\p{L}]{2,30}$";
    // Min 10 and max 11 chars long, doesnt support country codes.
    public static final String PHONE_NUMBER_REGEX = "^[0-9]{10,11}$";
}
