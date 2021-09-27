package dev.loanapplicationui.utilities;

public class Messages {
    // Negative
    public static final String NON_ALPHABETICAL_ERROR = "Only alphabetical characters are allowed and should be minimum 3 and maximum 30 characters long.";
    public static final String NOT_A_VALID_ID_ERROR = "Given ID is not valid, only numbers allowed with exact length of 11.";
    public static final String NOT_A_VALID_PHONE = "Given phone is not valid, only numbers allowed with min length of 10.";
    public static final String NOT_ELIGIBLE_FOR_CREDIT = "You are not eligible for credit at this moment, you may apply again after increasing your credit score above 500.";
    public static final String NOT_ELIGIBLE_RESPONSE_SMS = "Dear %s %s, you are not eligible for credit at this moment, you may apply again after increasing your credit score above 500.";
    public static final String POSITIVE_NUMBER_ERROR = "Should be higher then or equal to zero.";
    public static final String NON_NUMERIC_ERROR = "Only numbers allowed.";
    public static final String SCORE_LENGTH_ERROR = "Should be maximum 4 digits long.";
    public static final String INCOME_LENGTH_ERROR = "Should be maximum 12 digits long, no one is that rich.";
    // Positives
    public static final String APPROVAL_RESPONSE = "Your credit application is approved with the limit of %.2f TRY. Feel free to withdraw from our branches or ATMs.";
    public static final String APPROVAL_RESPONSE_SMS = "Dear %s %s, your credit application is approved with the limit of %.2f TRY. Feel free to withdraw from our branches or ATMs.";
}
