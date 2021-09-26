package dev.loanapplicationui.utilities;

public class StringConstants {
    // Chars only with minimum three and maximum thirty chars long in length
    public static final String CHAR_ONLY_REGEX_MIN_MAX = "^[\\p{L}]{2,30}$";
    // Min 10 and max 11 chars long, doesnt support country codes.
    public static final String PHONE_NUMBER_REGEX = "^\\s*-?[0-9]{10,11}\\s*$";
    // Loan Application Service
    public static final String LOAN_APP_API_URI = "http://localhost:9090/loan-application-service/api/loan-applications";
    public static final String FINDEX_CONSUMERS_API_URI = "http://localhost:9090/findex-inquiry-service/api/consumers";


}
