package dev.loanapplicationapi.service;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.DTO.response.EligibilityResponse;

public interface LoanApplicationService {
    EligibilityResponse checkEligibility(LoanApplicationRequest loanApplicationRequest);
}
