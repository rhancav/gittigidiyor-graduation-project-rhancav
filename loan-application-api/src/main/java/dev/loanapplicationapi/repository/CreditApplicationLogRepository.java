package dev.loanapplicationapi.repository;

import dev.loanapplicationapi.model.CreditApplicationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CreditApplicationLogRepository extends JpaRepository<CreditApplicationLog, Long>, PagingAndSortingRepository<CreditApplicationLog, Long> {
    List<CreditApplicationLog> findAllByConsumerIdentificationNumber(Long identificationNumber);
    List<CreditApplicationLog> findAllByConsumerIdentificationNumberOrderByCreationTimeDesc(Long identificationNumber);
    List<CreditApplicationLog> findAllByConsumerIdentificationNumberOrderByCreationTimeAsc(Long identificationNumber);
}
