package dev.findexinquiryservice.service.concrete;

import dev.findexinquiryservice.DTO.response.ScoreInquiryResponse;
import dev.findexinquiryservice.entity.Consumer;
import dev.findexinquiryservice.exceptions.ConsumerAlreadyExistsException;
import dev.findexinquiryservice.exceptions.ConsumerNotExistentException;
import dev.findexinquiryservice.exceptions.NotAValidIDException;
import dev.findexinquiryservice.repository.ConsumerRepository;
import dev.findexinquiryservice.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation class of the {@link ConsumerService} interface.
 * @author Erhan CAVDAR
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {
    private final ConsumerRepository consumerRepository;

    /**
     * {@inheritDoc}
     * @param consumer object to persist.
     * @return
     */
    @Override
    public Consumer save(Consumer consumer) {
        if (consumerExists(consumer.getIdentificationNumber())) {
            throw new ConsumerAlreadyExistsException("Consumer already exists with the given identification number.");
        }
        if (consumer.getCreditScore() == 0) {
            consumer.setCreditScore(calculateScore(consumer.getIdentificationNumber()));
        }
        return consumerRepository.save(consumer);
    }

    /**
     * {@inheritDoc}
     * @param consumer to be updated.
     */
    @Override
    @Transactional
    public void update(Long id, Consumer consumer) {
        // TODO Fix this!!!
        if (!consumerExists(consumer.getIdentificationNumber())) {
            throw new ConsumerNotExistentException("Could not found consumer with the given identification number.");
        }
        Consumer consumer1 = findByIdentificationNumber(id);
        consumer1.setCreditScore(consumer.getCreditScore());
        consumer1.setForename(consumer.getForename());
        consumer1.setSurname(consumer.getSurname());
        log.warn("Consumer data is: "+consumer1.toString());
        consumerRepository.save(consumer1);
    }

    /**
     * {@inheritDoc}
     * @param identificationNumber of the consumer.
     */
    @Override
    @Transactional
    public void delete(Long identificationNumber) {
        if (!consumerExists(identificationNumber)) {
            throw new ConsumerNotExistentException("Could not found consumer with the given identification number.");
        }
        consumerRepository.deleteByIdentificationNumber(identificationNumber);
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<Consumer> findAll() {
        return consumerRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * @param identificationNumber of the consumer.
     * @return
     */
    @Override
    public Consumer findByIdentificationNumber(Long identificationNumber) {
        return consumerRepository.findConsumerByIdentificationNumber(identificationNumber).orElseThrow(() -> new ConsumerNotExistentException("Could not found consumer with the given identification number."));
    }

    /**
     * {@inheritDoc}
     * @param identificationNumber the ID of the desired consumer.
     * @return
     */
    @Override
    public ScoreInquiryResponse getScoreByID(Long identificationNumber) {
        Consumer consumer = findByIdentificationNumber(identificationNumber);
        return new ScoreInquiryResponse(consumer.getCreditScore());
    }

    /**
     * Utility method to calculate credit score by the given
     * national id's last number. We are assuming that the national id
     * cannot end with a odd number. If not valid, throws {@link NotAValidIDException} exception.
     *
     * @param identificationNumber of the consumer
     * @return 550 if id ends with "2",
     * 1000 if id ends with "4",
     * 400 if id ends with "6",
     * 900 if id ends with "8" and 2000 if the id ends with "0".
     */
    private int calculateScore(Long identificationNumber) {
        int score;
        if (String.valueOf(identificationNumber).endsWith("2")) {
            score = 550;
        } else if (String.valueOf(identificationNumber).endsWith("4")) {
            score = 1000;
        } else if (String.valueOf(identificationNumber).endsWith("6")) {
            score = 400;
        } else if (String.valueOf(identificationNumber).endsWith("8")) {
            score = 900;
        } else if (String.valueOf(identificationNumber).endsWith("0")) {
            score = 2000;
        } else {
            throw new NotAValidIDException("National ID should not end with an odd number.");
        }
        return score;

    }

    /**
     * Checks if the consumer exists or notç
     *
     * @param identificationNumber of the consumer
     * @return true if it exists or else false.
     */
    private boolean consumerExists(Long identificationNumber) {
        log.warn("The id of the consumer is: "+identificationNumber);
        boolean result = consumerRepository.findConsumerByIdentificationNumber(identificationNumber).isPresent();
        log.warn("The returned result is: "+result);
        return result;
    }
}
