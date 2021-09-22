package dev.loanapplicationapi.service.concrete;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.DTO.response.EligibilityResponse;
import dev.loanapplicationapi.DTO.response.EligibleResponse;
import dev.loanapplicationapi.DTO.response.NotEligibleResponse;
import dev.loanapplicationapi.DTO.response.ScoreInquiryResponse;
import dev.loanapplicationapi.service.LoanApplicationService;
import dev.loanapplicationapi.utilities.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {
    @Autowired
    private final RestTemplate restTemplate;
    private final String CREDIT_SCORE_API_URL = "http://localhost:8081/api/consumers/";

    @Override
    public EligibilityResponse checkEligibility(LoanApplicationRequest loanApplicationRequest) {
        String queryURL= CREDIT_SCORE_API_URL+loanApplicationRequest.getIdentificationNumber().toString().trim();
        ScoreInquiryResponse scoreInquiryResponse = restTemplate.getForObject(queryURL, ScoreInquiryResponse.class);
        EligibilityTier eligibilityTier = getEligibilityTier(scoreInquiryResponse);
        EligibilityResponse eligibilityResponse;
        double creditLimit;
        if (eligibilityTier == EligibilityTier.A) {
            creditLimit = scoreInquiryResponse.getMonthlyIncome()*4D;
            eligibilityResponse = new EligibleResponse(String.format(Messages.APPROVAL_RESPONSE, creditLimit));
        } else if (eligibilityTier == EligibilityTier.B) {
            creditLimit = 20000D;
            eligibilityResponse = new EligibleResponse(String.format(Messages.APPROVAL_RESPONSE, creditLimit));
        } else if (eligibilityTier == EligibilityTier.C) {
            creditLimit = 10000D;
            eligibilityResponse = new EligibleResponse(String.format(Messages.APPROVAL_RESPONSE, creditLimit));
        } else {
            eligibilityResponse = new NotEligibleResponse();
        }
        return eligibilityResponse;
    }


    /**
     * Utility method to get the eligibility tier of the given inquiry object.
     *
     * @param scoreInquiryResponse
     * @return the eligibility tier.
     */
    private EligibilityTier getEligibilityTier(ScoreInquiryResponse scoreInquiryResponse) {
        int creditScore = scoreInquiryResponse.getScore();
        EligibilityTier eligibilityTier;
        double monthlyIncome = scoreInquiryResponse.getMonthlyIncome();
        if (creditScore > 500) {
            if (monthlyIncome < 5000) {
                eligibilityTier = EligibilityTier.C;
            } else {
                eligibilityTier = EligibilityTier.B;
            }
        } else if (creditScore >= 1000) {
            eligibilityTier = EligibilityTier.A;
        } else {
            eligibilityTier = EligibilityTier.D;
        }
        return eligibilityTier;
    }

    /**
     * Tier list for eligibility. "A" being the highest, without any major risk
     * and "D" being the lowest, meaning not eligible for credit.
     */
    private enum EligibilityTier {
        A,
        B,
        C,
        D
    }
}
