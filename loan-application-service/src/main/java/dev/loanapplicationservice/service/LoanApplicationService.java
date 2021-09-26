package dev.loanapplicationservice.service;

import dev.loanapplicationservice.DTO.request.LoanApplicationRequest;
import dev.loanapplicationservice.DTO.response.EligibilityResponse;
import dev.loanapplicationservice.DTO.response.EligibleResponse;
import dev.loanapplicationservice.DTO.response.NotEligibleResponse;

/**
 * Contains abstract methods which are related to operations on Loan Application process.
 * @author Erhan Cavdar
 */
public interface LoanApplicationService {
    /**
     * Processes the {@link LoanApplicationRequest} object so that
     * {@link EligibilityResponse} object is generated. Interacts with
     * the Findex Inquiry API to get the applicants credit score and
     * monthly income. Checks its eligibility tier and generates the corresponding
     * response object. After that it sends a mock notice to SMS Service API.
     * @param loanApplicationRequest incoming request, which contains fullname, phone and the national id of the applicant.
     * @return {@link EligibleResponse} or {@link NotEligibleResponse}
     */
    EligibilityResponse checkEligibility(LoanApplicationRequest loanApplicationRequest);

}
