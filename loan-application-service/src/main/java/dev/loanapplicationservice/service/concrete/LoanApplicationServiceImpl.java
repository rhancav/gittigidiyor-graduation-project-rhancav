package dev.loanapplicationservice.service.concrete;

import dev.loanapplicationservice.DTO.request.LoanApplicationRequest;
import dev.loanapplicationservice.DTO.request.ScoreInquiryRequest;
import dev.loanapplicationservice.DTO.response.*;
import dev.loanapplicationservice.exceptions.ConsumerNotFoundOnRemoteException;
import dev.loanapplicationservice.exceptions.NotAValidIDException;
import dev.loanapplicationservice.exceptions.NotNullableException;
import dev.loanapplicationservice.service.CreditApplicationLogService;
import dev.loanapplicationservice.service.LoanApplicationService;
import dev.loanapplicationservice.service.SMSService;
import dev.loanapplicationservice.utilities.Messages;
import dev.loanapplicationservice.utilities.StringConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * {@inheritDoc}
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService {
    private final RestTemplate restTemplate;
    private final SMSService smsService;
    private final CreditApplicationLogService creditApplicationLogService;
    @Value("${creditscore.api.uri}")
    public String CREDIT_SCORE_API_URL;

    /**
     * {@inheritDoc}
     *
     * @param loanApplicationRequest incoming request, which contains fullname, phone and the national id of the applicant.
     * @return
     */
    @Override
    public EligibilityResponse checkEligibility(LoanApplicationRequest loanApplicationRequest) {
        if(loanApplicationRequest == null){
            throw new NotNullableException("Loan Application Request cannot be null.");
        }
        // Cant end with an odd number
        if(loanApplicationRequest.getIdentificationNumber()%2 != 0){
            throw new NotAValidIDException("Not a valid identification number, cannot end with an odd number.");
        }
        // Attempt to get associated credit score
        ScoreInquiryRequest scoreInquiryRequest = ScoreInquiryRequest.builder()
                .forename(StringUtils.capitalize(loanApplicationRequest.getForename().toLowerCase()))   // Could be all lower case of course
                .surname(StringUtils.capitalize(loanApplicationRequest.getSurname().toLowerCase()))     // Also this
                .identificationNumber(loanApplicationRequest.getIdentificationNumber())
                .build();
        ScoreInquiryResponse scoreInquiryResponse = getScoreInquiryResponse(scoreInquiryRequest);
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

    /**
     * Gets the query response from the "remore" Findex Inquiry Api. Throws {@link ConsumerNotFoundOnRemoteException}
     * if the server responds with ConsumerNotFoundException.
     * @param scoreInquiryRequest containing basic consumer information
     * @return ScoreInquiryObject if the request is successfull.
     */
    public ScoreInquiryResponse getScoreInquiryResponse(ScoreInquiryRequest scoreInquiryRequest){
        ScoreInquiryResponse scoreInquiryResponse;
        // Target URL with path variable
        String queryURL = CREDIT_SCORE_API_URL;
        try{
            scoreInquiryResponse = restTemplate.postForObject(queryURL, scoreInquiryRequest, ScoreInquiryResponse.class);
        }
        catch (HttpClientErrorException e){
                throw new ConsumerNotFoundOnRemoteException(e.getMessage());
        }
        return scoreInquiryResponse;

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
