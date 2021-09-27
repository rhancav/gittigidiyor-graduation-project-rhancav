package dev.loanapplicationservice.service.concrete;

import dev.loanapplicationservice.DTO.request.LoanApplicationRequest;
import dev.loanapplicationservice.exceptions.NotFoundAnyApplicationsException;
import dev.loanapplicationservice.exceptions.NotNullableException;
import dev.loanapplicationservice.exceptions.UnsupportedFilterException;
import dev.loanapplicationservice.model.Consumer;
import dev.loanapplicationservice.model.CreditApplicationLog;
import dev.loanapplicationservice.repository.CreditApplicationLogRepository;
import dev.loanapplicationservice.service.CreditApplicationLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;

/**
 * {@inheritDoc}
 */
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
                .forename(StringUtils.capitalize(loanApplicationRequest.getForename().toLowerCase()))
                .surname(StringUtils.capitalize(loanApplicationRequest.getSurname().toLowerCase()))
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

    /**
     * {@inheritDoc}
     * @param identificationNumber
     * @return
     */
    @Override
    public List<CreditApplicationLog> findAllByID(Long identificationNumber, String filter) {
        List<CreditApplicationLog> creditApplicationLogs;
        if(identificationNumber == null || filter == null){
            throw new NotNullableException("ID and filter cannot be null.");
        }
        if(creditApplicationLogRepository.findAll().isEmpty()){
            throw new NotFoundAnyApplicationsException("Not found any application records for the given identification number.");
        }
        if(filter.equalsIgnoreCase("ASC")){
            creditApplicationLogs = creditApplicationLogRepository.findAllByConsumerIdentificationNumberOrderByCreationTimeAsc(identificationNumber);
        }
        else if(filter.equalsIgnoreCase("DESC")){
            creditApplicationLogs = creditApplicationLogRepository.findAllByConsumerIdentificationNumberOrderByCreationTimeDesc(identificationNumber);
        }
        else if(filter.equalsIgnoreCase("LAST")){
            creditApplicationLogs = List.of(creditApplicationLogRepository.findAllByConsumerIdentificationNumberOrderByCreationTimeDesc(identificationNumber).get(0));
        }
        else if (filter.equalsIgnoreCase("UNFILTERED")){
            creditApplicationLogs = creditApplicationLogRepository.findAllByConsumerIdentificationNumber(identificationNumber);
        }
        else {
            throw new UnsupportedFilterException("Given filter is not accepted, available filters are: DESC, ASC, LAST.");
        }
        return  creditApplicationLogs;
    }
}
