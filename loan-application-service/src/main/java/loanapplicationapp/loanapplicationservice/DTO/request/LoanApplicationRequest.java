package loanapplicationapp.loanapplicationservice.DTO.request;

import loanapplicationapp.loanapplicationservice.utilities.ErrorMessages;
import loanapplicationapp.loanapplicationservice.utilities.StringConstants;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Basic type to handle loan application post request, contains customers
 * identification number, forename, surname and phone.
 */
@Data
public class LoanApplicationRequest {
    @NotBlank
    @Digits(integer = 11, fraction = 0, message = ErrorMessages.NOT_A_VALID_ID_ERROR)
    private final Long identificationNumber;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = ErrorMessages.NON_ALPHABETICAL_ERROR)
    private final String forename;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = ErrorMessages.NON_ALPHABETICAL_ERROR)
    private final String surname;
    @NotBlank
    @Range(min = 10, max = 11, message = ErrorMessages.NOT_A_VALID_PHONE)
    private final Long phone;
}
