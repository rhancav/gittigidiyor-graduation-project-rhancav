package dev.loanapplicationservice.DTO.request;

import dev.loanapplicationservice.utilities.Messages;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
}
