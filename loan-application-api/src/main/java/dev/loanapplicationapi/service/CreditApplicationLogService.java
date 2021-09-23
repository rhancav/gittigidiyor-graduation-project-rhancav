package dev.loanapplicationapi.service;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;

/**
 * Contains abstract methods which are related to CRUD operations on Credit Applicaton Logs.
 * @author Erhan Cavdar
 */
public interface CreditApplicationLogService {
    /**
     * Gets the consumer related info within {@link LoanApplicationRequest} and persists them to the DB
     * along with elgilibility status and credit limit(if it is eligibile or else null).
     * @param loanApplicationRequest contains basic consumer info
     * @param creditLimit credit limit of the consumer
     * @param eligible eligilibility status
     */
    void persistApplicationLog(LoanApplicationRequest loanApplicationRequest, double creditLimit, boolean eligible);
}
