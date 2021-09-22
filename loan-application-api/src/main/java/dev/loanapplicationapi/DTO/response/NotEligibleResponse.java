package dev.loanapplicationapi.DTO.response;

import dev.loanapplicationapi.utilities.Messages;
import lombok.Getter;

@Getter
public class NotEligibleResponse extends EligibilityResponse {
    public NotEligibleResponse() {
        super(EligibilityStatus.NOT_ELIGIBLE, Messages.NOT_ELIGIBLE_FOR_CREDIT);
    }
}
