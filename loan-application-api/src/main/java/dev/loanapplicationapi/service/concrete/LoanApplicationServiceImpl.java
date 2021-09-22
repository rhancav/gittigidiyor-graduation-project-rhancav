package dev.loanapplicationapi.service.concrete;

import dev.loanapplicationapi.DTO.response.EligibilityResponse;
import dev.loanapplicationapi.DTO.response.EligibleResponse;
import dev.loanapplicationapi.DTO.response.NotEligibleResponse;
import dev.loanapplicationapi.utilities.Messages;
import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.DTO.response.ScoreInquiryResponse;
import dev.loanapplicationapi.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {
    private final RestTemplate restTemplate;
    private final String CREDIT_SCORE_API_URL = "";

    @Override
    public EligibilityResponse checkEligibility(LoanApplicationRequest loanApplicationRequest) {
        ScoreInquiryResponse scoreInquiryResponse = restTemplate.getForObject(CREDIT_SCORE_API_URL, ScoreInquiryResponse.class);
        EligibilityTier eligibilityTier = getEligibilityTier(scoreInquiryResponse);
        EligibilityResponse eligibilityResponse;
        if (eligibilityTier == EligibilityTier.A) {
            eligibilityResponse = new EligibleResponse(String.format(Messages.APPROVAL_RESPONSE, scoreInquiryResponse.getMonthlyIncome() * 4));
        } else if (eligibilityTier == EligibilityTier.B) {
            eligibilityResponse = new EligibleResponse(String.format(Messages.APPROVAL_RESPONSE, 20000));
        } else if (eligibilityTier == EligibilityTier.C) {
            eligibilityResponse = new EligibleResponse(String.format(Messages.APPROVAL_RESPONSE, 10000));
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
