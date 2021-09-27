package dev.loanapplicationservice.utilities;

public class StringConstants {
    // Chars only with minimum three and maximum thirty chars long in length
    public static final String CHAR_ONLY_REGEX_MIN_MAX = "^[\\p{L}]{2,30}$";
    // Min 10 and max 11 chars long, doesnt support country codes.
    public static final String PHONE_NUMBER_REGEX = "^[0-9]{10,11}$";
    // SMS Service API
    public static final String SMS_SERVICE_API_URL = "http://localhost:9090/sms-service/api/sms-dispatcher/";
    // Findex API
    public static final String CREDIT_SCORE_API_URL = "http://localhost:9090/findex-inquiry-service/api/credit-score-inquiry";

}
