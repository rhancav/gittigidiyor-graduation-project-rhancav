package dev.loanapplicationservice.DTO.response;

import dev.loanapplicationservice.utilities.Messages;
import lombok.Getter;

/**
 * {@link EligibleResponse}
 * @author Erhan Cavdar
 */
@Getter
public class NotEligibleResponse extends EligibilityResponse {
    public NotEligibleResponse() {
        super(EligibilityStatus.NOT_ELIGIBLE.value, Messages.NOT_ELIGIBLE_FOR_CREDIT);
    }
}
