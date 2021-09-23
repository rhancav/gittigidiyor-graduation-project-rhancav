package dev.loanapplicationapi.service.concrete;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.DTO.request.SMSRequest;
import dev.loanapplicationapi.DTO.response.*;
import dev.loanapplicationapi.repository.CreditApplicationLogRepository;
import dev.loanapplicationapi.service.CreditApplicationLogService;
import dev.loanapplicationapi.service.LoanApplicationService;
import dev.loanapplicationapi.service.SMSService;
import dev.loanapplicationapi.utilities.Messages;
import dev.loanapplicationapi.utilities.StringConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService {
    private final RestTemplate restTemplate;
    private final SMSService smsService;
    private final CreditApplicationLogService creditApplicationLogService;

    /**
     * {@inheritDoc}
     *
     * @param loanApplicationRequest incoming request, which contains fullname, phone and the national id of the applicant.
     * @return
     */
    @Override
    public EligibilityResponse checkEligibility(LoanApplicationRequest loanApplicationRequest) {
        // Target URL with path variable
        String queryURL = StringConstants.CREDIT_SCORE_API_URL + loanApplicationRequest.getIdentificationNumber().toString().trim();
        // Attempt to get associated credit score
        ScoreInquiryResponse scoreInquiryResponse = restTemplate.getForObject(queryURL, ScoreInquiryResponse.class);
        // Get the eligibility tier
        EligibilityTier eligibilityTier = getEligibilityTier(scoreInquiryResponse, loanApplicationRequest.getMonthlyIncome());
        // Uninitialized EligibilityResponse object, to be set later in the if-else tree
        EligibilityResponse eligibilityResponse;
        boolean eligible = true;
        double creditLimit = 0;
        // Highest
        if (eligibilityTier == EligibilityTier.A) {
            // 4x for the highest tier
            creditLimit = loanApplicationRequest.getMonthlyIncome()*4;
            eligibilityResponse = new EligibleResponse(String.format(Messages.APPROVAL_RESPONSE, creditLimit));
        } else if (eligibilityTier == EligibilityTier.B) {
            creditLimit = 20000D;
            eligibilityResponse = new EligibleResponse(String.format(Messages.APPROVAL_RESPONSE, creditLimit));
        }
        // Lowest
        else if (eligibilityTier == EligibilityTier.C) {
            creditLimit = 10000D;
            eligibilityResponse = new EligibleResponse(String.format(Messages.APPROVAL_RESPONSE, creditLimit));
        }
        // Not eligible
        else {
            eligibilityResponse = new NotEligibleResponse();
            eligible = false;
        }
        // Send mock notice
        smsService.sendApplicationNotice(loanApplicationRequest, creditLimit, eligible);
        // Log
        creditApplicationLogService.persistApplicationLog(loanApplicationRequest, creditLimit, eligible);
        return eligibilityResponse;
    }
    private void persistCreditApplicationLog(){

    }

    /**
     * Utility method to get the eligibility tier of the given inquiry object.
     *
     * @param scoreInquiryResponse
     * @return the eligibility tier.
     */
    private EligibilityTier getEligibilityTier(ScoreInquiryResponse scoreInquiryResponse, double monthlyIncome) {
        int creditScore = scoreInquiryResponse.getScore();
        EligibilityTier eligibilityTier;
        if (creditScore >= 1000) {
            eligibilityTier = EligibilityTier.A;
        }
        else if (creditScore > 500) {
            if (monthlyIncome < 5000) {
                eligibilityTier = EligibilityTier.C;
            } else {
                eligibilityTier = EligibilityTier.B;
            }}
        else {
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
