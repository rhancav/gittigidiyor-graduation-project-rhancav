package dev.loanapplicationui.DTO.request;

import dev.loanapplicationui.utilities.Messages;
import dev.loanapplicationui.utilities.StringConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * Basic type to handle loan application post request, contains customers
 * identification number, forename, surname and phone.
 * @author Erhan Cavdar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationRequest {
    @NotNull
    @Digits(integer = 11, fraction = 0, message = Messages.NOT_A_VALID_ID_ERROR)
    @Min(value = 10000000000L, message = Messages.NOT_A_VALID_ID_ERROR)
    private Long identificationNumber;
    @NotBlank
    @Pattern(regexp = StringConstants.FORENAME_REGEX, message = Messages.NON_ALPHABETICAL_ERROR)
    private String forename;
    @NotBlank
    @Pattern(regexp = StringConstants.SURNAME_REGEX, message = Messages.NON_ALPHABETICAL_ERROR)
    private String surname;
    @NotBlank
    @Pattern(regexp = StringConstants.PHONE_NUMBER_REGEX, message = Messages.NOT_A_VALID_PHONE)
    private String phone;
    private double monthlyIncome;
}
