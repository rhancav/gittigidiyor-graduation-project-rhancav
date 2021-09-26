package dev.smsservice.DTO.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Base response model for sms delivery request.
 * @author Erhan Cavdar
 */
@Data
@ApiModel(description = "Base response model for sms delivery request.")
public abstract class AbstractResponse {
    @ApiModelProperty(example = "SMS delivered successfully.")
    private final String message;
    @ApiModelProperty(example = "True", notes = "True or False")
    private final boolean success;
}
