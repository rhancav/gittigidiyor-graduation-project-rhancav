package dev.loanapplicationapi.repository;

import dev.loanapplicationapi.model.CreditApplicationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationLogRepository extends JpaRepository<CreditApplicationLog, Long>{
}