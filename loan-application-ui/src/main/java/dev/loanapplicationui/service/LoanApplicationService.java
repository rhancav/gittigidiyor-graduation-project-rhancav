package dev.loanapplicationui.service;

import dev.loanapplicationui.DTO.CreditApplicationLog;
import dev.loanapplicationui.DTO.request.LoanApplicationRequest;
import dev.loanapplicationui.DTO.response.CreditEligibilityResponse;

import java.util.List;

public interface LoanApplicationService {
    CreditEligibilityResponse postApplication(LoanApplicationRequest loanApplicationRequest);
    List<CreditApplicationLog> getLogsByID(long identificationNumber);
}
