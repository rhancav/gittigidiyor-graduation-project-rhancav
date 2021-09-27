package dev.findexinquiryservice.DTO.request;

import dev.findexinquiryservice.utility.Messages;
import dev.findexinquiryservice.utility.StringConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import javax.validation.constraints.*;
/**
 * @author Erhan CAVDAR
 */
@Value
@ApiModel(description = "Carries the consumer forename, surname and identificationNumber to match the related DB entity.")
public class CreditScoreInquiryRequest {
    @NotBlank
    @Pattern(regexp = StringConstants.UNICODE_CHAR_ONLY, message = Messages.NON_ALPHABETICAL_ERROR)
    @ApiModelProperty(example = "Osman",
            required = true,
            dataType = "String",
            notes = "It is UNICODE supported. Should be at least 2, maximum 30 chars long."
    )
    String forename;
    @NotBlank
    @Pattern(regexp = StringConstants.UNICODE_CHAR_ONLY, message = Messages.NON_ALPHABETICAL_ERROR)
    @ApiModelProperty(example = "Akca",
            required = true,
            dataType = "String",
            notes = "It is UNICODE supported. Should be at least 2, maximum 30 chars long."
    )
    String surname;
    @NotNull
    @Digits(integer = 11, fraction = 0, message = Messages.NOT_A_VALID_ID_ERROR)
    @Min(value = 10000000000L, message = Messages.NOT_A_VALID_ID_ERROR)
    @ApiModelProperty(example = "15216215276",
            required = true,
            dataType = "Long",
            notes = "Should not exceed 11 digits and cannot be lower then 11 digits long.Last digit cannot be an odd number."
    )
    Long identificationNumber;
}
