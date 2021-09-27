package dev.findexinquiryservice.repository;

import dev.findexinquiryservice.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Contains base CRUD operations.
 * @author Erhan CAVDAR
 */
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    // Query Method to get consumer by identification number
    Optional<Consumer> findConsumerByIdentificationNumber(Long identificationNumber);

    // Query Method to delete consumer by his/her identification number
    void deleteByIdentificationNumber(Long identificationNumber);

    // Return true if the consumer is existent and all the fields match
    boolean existsByForenameAndSurnameAndIdentificationNumber(String forename, String surname, Long identificationNumber);
}
