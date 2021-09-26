package dev.loanapplicationservice.DTO.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * SMS Request response object.
 * @author Erhan Cavdar
 */
@Data
public class SMSDispatcherResponse {
    @ApiModelProperty(example = "SMS Sent successfully.")
    private final String message;
    @ApiModelProperty(example = "True")
    private final boolean success;
}
