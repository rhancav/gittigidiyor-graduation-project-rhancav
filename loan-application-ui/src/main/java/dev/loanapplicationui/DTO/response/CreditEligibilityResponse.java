package dev.loanapplicationui.DTO.response;

import lombok.Value;

@Value
public class CreditEligibilityResponse {
    String eligibility;
    String message;
}
