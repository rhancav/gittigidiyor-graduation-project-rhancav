package dev.loanapplicationui.service.concrete;

import dev.loanapplicationui.DTO.CreditApplicationLog;
import dev.loanapplicationui.DTO.request.LoanApplicationRequest;
import dev.loanapplicationui.DTO.response.CreditEligibilityResponse;
import dev.loanapplicationui.service.LoanApplicationService;
import dev.loanapplicationui.utilities.StringConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService {
    private final RestTemplate restTemplate;
    @Value("${loan.application.service.uri}")
    public String LOAN_APPlICATION_API_URI;

    @Override
    public CreditEligibilityResponse postApplication(LoanApplicationRequest loanApplicationRequest) {
        log.warn("In service layer: "+loanApplicationRequest.toString());
        return restTemplate.postForEntity(LOAN_APPlICATION_API_URI, loanApplicationRequest, CreditEligibilityResponse.class).getBody();
    }

    @Override
    public List<CreditApplicationLog> getLogsByID(long identificationNumber, String filter) {
        List<CreditApplicationLog> logs = restTemplate.getForEntity(LOAN_APPlICATION_API_URI+"/"+identificationNumber+"?filter="+filter, List.class).getBody();
        log.warn("Logs at service level are: "+logs);
        return logs;
    }
}
