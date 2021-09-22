package loanapplicationapp.loanapplicationservice.service;

import loanapplicationapp.loanapplicationservice.DTO.request.LoanApplicationRequest;
import loanapplicationapp.loanapplicationservice.DTO.response.EligibilityResponse;

public interface LoanApplicationService {
    EligibilityResponse checkEligibility(LoanApplicationRequest loanApplicationRequest);
}
