package dev.findexinquiryapi.service;

import dev.findexinquiryapi.DTO.response.ScoreInquiryResponse;
import dev.findexinquiryapi.entity.Consumer;
import dev.findexinquiryapi.exceptions.ConsumerAlreadyExistsException;
import dev.findexinquiryapi.exceptions.ConsumerNotExistentException;

import java.util.List;

/** Base interface which contains persistance operations  associated with the consumer datas.
 * @author Erhan CAVDAR
 */
public interface ConsumerService {
    /**
     * Inserts the given Consumer object to the database if it is not null.
     * First checks if the consumer already exists with the given ID or not.
     * Throws {@link ConsumerAlreadyExistsException} if it is. Also, calls the
     * calculateScore() method if the score is not passed already.
     * @param consumer object to persist.
     * @return the persisted consumer object.
     */
    Consumer save(Consumer consumer);

    /**
     * If the given consumer exists, performs update. If not
     * throws {@link ConsumerNotExistentException} exception.
     * @param consumer to be updated.
     */
    void update(Long id, Consumer consumer);

    /**
     * If the given consumer exists, performs delete by the national ID. If not
     * throws {@link ConsumerNotExistentException} exception.
     * @param identificationNumber of the consumer.
     */
    void delete(Long identificationNumber);

    /**
     *
     * @return the list of all consumers.
     */
    List<Consumer> findAll();

    /**
     * Finds the consumer with the given nationalID.
     * Throws {@link ConsumerNotExistentException} if not found.
     * @param identificationNumber of the consumer.
     * @return the found consumer object.
     */
    Consumer findByIdentificationNumber(Long identificationNumber);

    /**
     * Gets the credit score information wrapped
     * in the {@link ScoreInquiryResponse} object.
     * @param identificationNumber the ID of the desired consumer.
     * @return {@link ScoreInquiryResponse} object.
     */
    ScoreInquiryResponse getScoreByID(Long identificationNumber);
}
