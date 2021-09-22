package dev.loanapplicationapi.service.concrete;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.DTO.request.SMSRequest;
import dev.loanapplicationapi.DTO.response.*;
import dev.loanapplicationapi.service.LoanApplicationService;
import dev.loanapplicationapi.utilities.Messages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService {
    @Autowired
    private final RestTemplate restTemplate;
    private final String CREDIT_SCORE_API_URL = "http://localhost:8081/api/consumers/";
    private final String SMS_SERVICE_API_URL = "http://localhost:8082/api/sms-dispatcher/";

    /**
     * {@inheritDoc}
     *
     * @param loanApplicationRequest incoming request, which contains fullname, phone and the national id of the applicant.
     * @return
     */
    @Override
    public EligibilityResponse checkEligibility(LoanApplicationRequest loanApplicationRequest) {
        // Target URL with path variable
        String queryURL = CREDIT_SCORE_API_URL + loanApplicationRequest.getIdentificationNumber().toString().trim();
        // Attempt to get associated credit score
        ScoreInquiryResponse scoreInquiryResponse = restTemplate.getForObject(queryURL, ScoreInquiryResponse.class);
        // Get the eligibility tier
        EligibilityTier eligibilityTier = getEligibilityTier(scoreInquiryResponse);
        // Uninitialized EligibilityResponse object, to be set later in the if-else tree
        EligibilityResponse eligibilityResponse;
        boolean eligible = true;
        double creditLimit = 0;
        // Highest
        if (eligibilityTier == EligibilityTier.A) {
            // 4x for the highest tier
            creditLimit = scoreInquiryResponse.getMonthlyIncome() * 4D;
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
        sendApplicationNotice(loanApplicationRequest, creditLimit, eligible);
        return eligibilityResponse;
    }

    /**
     * Gets the consumers forename, surname and phonenumber from {@link LoanApplicationRequest} object to create sms message according to
     * status of "eligible" parameter. After generating the message, sends it to the SMSDispatcher Service which is implemented under
     * sms-service-api microservice.
     *
     * @param loanApplicationRequest of the incoming applicaton request
     * @param creditLimit            of the consumer according to their eligibility tier
     * @param eligible               eligibility status
     */
    private void sendApplicationNotice(LoanApplicationRequest loanApplicationRequest, double creditLimit, boolean eligible) {
        String message;
        if (eligible) {
            message = String.format(Messages.APPROVAL_RESPONSE_SMS, loanApplicationRequest.getForename(), loanApplicationRequest.getSurname(), creditLimit);
        }
        message = String.format(Messages.NOT_ELIGIBLE_RESPONSE_SMS, loanApplicationRequest.getForename(), loanApplicationRequest.getSurname());
        // New request object
        SMSRequest smsRequest = new SMSRequest(loanApplicationRequest.getPhone(), message);
        // Send request object
        SMSDispatcherResponse smsDispatcherResponse = restTemplate.postForObject(SMS_SERVICE_API_URL, smsRequest, SMSDispatcherResponse.class);
        // Log
        log.info(smsDispatcherResponse.getMessage());
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
