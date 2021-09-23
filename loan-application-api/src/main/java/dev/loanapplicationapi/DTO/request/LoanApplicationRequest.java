package dev.loanapplicationapi.DTO.request;

import dev.loanapplicationapi.utilities.Messages;
import dev.loanapplicationapi.utilities.StringConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * Basic type to handle loan application post request, contains customers
 * identification number, forename, surname and phone.
 */
@Data
@Builder
public class LoanApplicationRequest {
    @NotNull
    @Digits(integer = 11, fraction = 0, message = Messages.NOT_A_VALID_ID_ERROR)
    @Min(value = 10000000000L, message = Messages.NOT_A_VALID_ID_ERROR)
    private Long identificationNumber;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = Messages.NON_ALPHABETICAL_ERROR)
    private String forename;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = Messages.NON_ALPHABETICAL_ERROR)
    private String surname;
    @NotBlank
    @Pattern(regexp = StringConstants.PHONE_NUMBER_REGEX, message = Messages.NOT_A_VALID_PHONE)
    private String phone;
    @ApiModelProperty(example = "2750",
            dataType = "Double"
    )
    private double monthlyIncome;
}
