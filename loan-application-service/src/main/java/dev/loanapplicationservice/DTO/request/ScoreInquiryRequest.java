package dev.loanapplicationservice.DTO.request;

import dev.loanapplicationservice.utilities.Messages;
import dev.loanapplicationservice.utilities.StringConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @author Erhan Cavdar
 */
@Data
@Builder
@Valid
public class ScoreInquiryRequest {
    @NotNull
    @Valid
    @Digits(integer = 11, fraction = 0, message = Messages.NOT_A_VALID_ID_ERROR)
    @Min(value = 10000000000L, message = Messages.NOT_A_VALID_ID_ERROR)
    @ApiModelProperty(example = "31271262080", required = true)
    private Long identificationNumber;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = Messages.NON_ALPHABETICAL_ERROR)
    @ApiModelProperty(example = "Osman",
            required = true,
            dataType = "String",
            notes = "It is UNICODE supported. Should be at least 2, maximum 30 chars long."
    )
    private String forename;
    @NotBlank
    @Pattern(regexp = StringConstants.CHAR_ONLY_REGEX_MIN_MAX, message = Messages.NON_ALPHABETICAL_ERROR)
    @ApiModelProperty(example = "Osman",
            required = true,
            dataType = "String",
            notes = "It is UNICODE supported. Should be at least 2, maximum 30 chars long."
    )
    private String surname;
}
