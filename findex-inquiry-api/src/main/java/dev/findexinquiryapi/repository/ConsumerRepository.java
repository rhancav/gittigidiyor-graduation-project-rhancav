package dev.findexinquiryapi.repository;

import dev.findexinquiryapi.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    Optional<Consumer> findConsumerByIdentificationNumber(Long identificationNumber);
    void deleteByIdentificationNumber(Long identificationNumber);
}
