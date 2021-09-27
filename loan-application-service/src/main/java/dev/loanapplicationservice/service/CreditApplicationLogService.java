package dev.loanapplicationservice.service;

import dev.loanapplicationservice.DTO.request.LoanApplicationRequest;
import dev.loanapplicationservice.model.CreditApplicationLog;

import java.util.List;

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

    /**
     * List all the past applications associated with the given identification number sorted by creation date.
     * Can take three kind of filters: DESC to sort in descending order, ASC to sort
     * ascending order and LAST to get the latest application. If none given, it is UNFILTERED by default.
     * @return the list of applications
     */
    List<CreditApplicationLog> findAllByID(Long identificationNumber, String filter);
}
