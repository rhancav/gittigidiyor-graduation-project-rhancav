package dev.loanapplicationservice.DTO.request;

import dev.loanapplicationservice.utilities.Messages;
import dev.loanapplicationservice.utilities.StringConstants;
import io.swagger.annotations.ApiModelProperty;
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
@AllArgsConstructor
@NoArgsConstructor
public class LoanApplicationRequest {
    @NotNull
    @Digits(integer = 11, fraction = 0, message = Messages.NOT_A_VALID_ID_ERROR)
    @Min(value = 10000000000L, message = Messages.NOT_A_VALID_ID_ERROR)
    @ApiModelProperty(example = "34756891202", required = true, dataType = "Long")
    private Long identificationNumber;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = Messages.NON_ALPHABETICAL_ERROR)
    @ApiModelProperty(example = "Osman", required = true, dataType = "String")
    private String forename;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = Messages.NON_ALPHABETICAL_ERROR)
    @ApiModelProperty(example = "Kavala", required = true, dataType = "String")
    private String surname;
    @NotBlank
    @Pattern(regexp = StringConstants.PHONE_NUMBER_REGEX, message = Messages.NOT_A_VALID_PHONE)
    @ApiModelProperty(example = "05387062315", required = true, dataType = "String")
    private String phone;
    @ApiModelProperty(example = "2750",
            dataType = "Double"
    )
    private double monthlyIncome;
}
