package loanapplicationapp.loanapplicationservice.DTO.request;

import loanapplicationapp.loanapplicationservice.utilities.ErrorMessages;
import loanapplicationapp.loanapplicationservice.utilities.StringConstants;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * Basic type to handle loan application post request, contains customers
 * identification number, forename, surname and phone.
 */
@Data
@Builder
public class LoanApplicationRequest {
    @NotNull
    @Digits(integer = 11, fraction = 0, message = ErrorMessages.NOT_A_VALID_ID_ERROR)
    private final Long identificationNumber;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = ErrorMessages.NON_ALPHABETICAL_ERROR)
    private final String forename;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = ErrorMessages.NON_ALPHABETICAL_ERROR)
    private final String surname;
    @NotBlank
    @Pattern(regexp = StringConstants.PHONE_NUMBER_REGEX, message = ErrorMessages.NOT_A_VALID_PHONE)
    private final String phone;
}
