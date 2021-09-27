package dev.loanapplicationservice.DTO.request;

import dev.loanapplicationservice.utilities.Messages;
import dev.loanapplicationservice.utilities.StringConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.time.LocalDate;

/**
 * Sms request model.
 * @author Erhan Cavdar
 */
@Data
@Valid
public class SMSRequest {
    @NotBlank
    @Pattern(regexp = StringConstants.PHONE_NUMBER_REGEX, message = Messages.NOT_A_VALID_PHONE)
    @ApiModelProperty(example = "05387062315", required = true, dataType = "String")
    private final String phoneNumber;
    @NotBlank
    @ApiModelProperty(example = "You are eligible for credit.", required = true)
    private final String message;
    // Creation time
    private final Instant sentTime = Instant.now();
}


