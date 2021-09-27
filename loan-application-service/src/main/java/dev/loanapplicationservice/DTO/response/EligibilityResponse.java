package dev.loanapplicationservice.DTO.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Basic eligibility response which is extended by two sub-types as
 * {@link EligibleResponse} and {@link NotEligibleResponse}. Clearer reading for me.
 * @author Erhan Cavdar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EligibilityResponse {
    @ApiModelProperty(example = "Eligible")
    private String eligibility;
    @ApiModelProperty(example = "You are eligible with the limit of 10000 TL")
    private String message;
}
