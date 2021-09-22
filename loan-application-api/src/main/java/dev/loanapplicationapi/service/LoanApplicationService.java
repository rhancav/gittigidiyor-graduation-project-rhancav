package dev.loanapplicationapi.service;

import dev.loanapplicationapi.DTO.response.EligibilityResponse;
import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;

public interface LoanApplicationService {
    EligibilityResponse checkEligibility(LoanApplicationRequest loanApplicationRequest);
}
