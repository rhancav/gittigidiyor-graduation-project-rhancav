package dev.loanapplicationapi.service.concrete;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.DTO.request.SMSRequest;
import dev.loanapplicationapi.DTO.response.SMSDispatcherResponse;
import dev.loanapplicationapi.service.SMSService;
import dev.loanapplicationapi.utilities.Messages;
import dev.loanapplicationapi.utilities.StringConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@Slf4j
@RequiredArgsConstructor
public class SMSServiceImpl implements SMSService {
    private final RestTemplate restTemplate;

    /**
     * {@inheritDoc}
     * @param loanApplicationRequest of the incoming applicaton request
     * @param creditLimit            of the consumer according to their eligibility tier
     * @param eligible               eligibility status
     */
    @Override
    public void sendApplicationNotice(LoanApplicationRequest loanApplicationRequest, double creditLimit, boolean eligible) {
        String message;
        if (eligible) {
            message = String.format(Messages.APPROVAL_RESPONSE_SMS, loanApplicationRequest.getForename(), loanApplicationRequest.getSurname(), creditLimit);
        }
        message = String.format(Messages.NOT_ELIGIBLE_RESPONSE_SMS, loanApplicationRequest.getForename(), loanApplicationRequest.getSurname());
        // New request object
        SMSRequest smsRequest = new SMSRequest(loanApplicationRequest.getPhone(), message);
        // Send request object
        SMSDispatcherResponse smsDispatcherResponse = restTemplate.postForObject(StringConstants.SMS_SERVICE_API_URL, smsRequest, SMSDispatcherResponse.class);
        // Log
        log.info(smsDispatcherResponse.getMessage());
    }
}
