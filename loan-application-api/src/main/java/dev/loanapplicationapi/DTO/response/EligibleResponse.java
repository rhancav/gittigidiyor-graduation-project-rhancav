package dev.loanapplicationapi.DTO.response;

import lombok.Getter;

@Getter
public class EligibleResponse extends EligibilityResponse {
    public EligibleResponse(String message) {
        super(EligibilityStatus.ELIGIBLE, message);
    }
}
