package dev.loanapplicationservice.repository;

import dev.loanapplicationservice.model.CreditApplicationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * {@link CreditApplicationLog} repository.
 */
public interface CreditApplicationLogRepository extends JpaRepository<CreditApplicationLog, Long>, PagingAndSortingRepository<CreditApplicationLog, Long> {
    /**
     * Get the list of logs by id.
     * @param identificationNumber of the consumer.
     * @return the list of consumers.
     */
    List<CreditApplicationLog> findAllByConsumerIdentificationNumber(Long identificationNumber);
    /**
     * Get the list of logs by id in descending order.
     * @param identificationNumber of the consumer.
     * @return the list of consumers.
     */
    List<CreditApplicationLog> findAllByConsumerIdentificationNumberOrderByCreationTimeDesc(Long identificationNumber);
    /**
     * Get the list of logs by id in ascending order.
     * @param identificationNumber of the consumer.
     * @return the list of consumers.
     */
    List<CreditApplicationLog> findAllByConsumerIdentificationNumberOrderByCreationTimeAsc(Long identificationNumber);
}
