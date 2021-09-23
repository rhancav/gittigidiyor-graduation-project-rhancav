package dev.loanapplicationapi.service.concrete;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.model.Consumer;
import dev.loanapplicationapi.model.CreditApplicationLog;
import dev.loanapplicationapi.repository.CreditApplicationLogRepository;
import dev.loanapplicationapi.service.CreditApplicationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CreditApplicationLogImpl implements CreditApplicationLogService {
    private final CreditApplicationLogRepository creditApplicationLogRepository;

    /**
     * {@inheritDoc}
     * @param loanApplicationRequest contains basic consumer info
     * @param creditLimit credit limit of the consumer
     * @param eligible eligilibility status
     */
    @Transactional
    public void persistApplicationLog(LoanApplicationRequest loanApplicationRequest, double creditLimit, boolean eligible){
        // Consumer Info
        Consumer consumer = Consumer.builder()
                .forename(loanApplicationRequest.getForename())
                .surname(loanApplicationRequest.getSurname())
                .identificationNumber(loanApplicationRequest.getIdentificationNumber())
                .monthlyIncome(loanApplicationRequest.getMonthlyIncome())
                .build();
        // Log to console for debugging purposes
        log.info(String.format("Embedded consumer info: %s", consumer.toString()));
        // If eligible "Eligible" if not "Not Eligible"
        String result = eligible ? "Eligible": "Not Eligible";
        // Sets limit to zero if not eligible
        if(!eligible){
            creditLimit = 0;
        }
        // Build the log object
        CreditApplicationLog creditApplicationLog = CreditApplicationLog.builder()
                .consumer(consumer)
                .creditLimit(creditLimit)
                .result(result)
                .build();
        // Log to console for debugging purposes
        log.info(String.format("Embedded consumer info: %s", creditApplicationLog.toString()));
        // Persist to DB
        creditApplicationLogRepository.save(creditApplicationLog);
    }
}