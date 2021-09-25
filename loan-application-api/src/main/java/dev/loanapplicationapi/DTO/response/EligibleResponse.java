package dev.loanapplicationapi.DTO.response;

import lombok.Getter;

/**
 * {@link EligibleResponse}
 * @author Erhan Cavdar
 */
@Getter
public class EligibleResponse extends EligibilityResponse {
    public EligibleResponse(String message) {
        super(EligibilityStatus.ELIGIBLE.value, message);
    }
}
