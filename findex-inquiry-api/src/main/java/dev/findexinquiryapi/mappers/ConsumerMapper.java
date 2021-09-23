package dev.findexinquiryapi.mappers;

import dev.findexinquiryapi.DTO.request.ConsumerCreationRequest;
import dev.findexinquiryapi.entity.Consumer;

/**
 * Contains custom, hand-written mapper methods.
 * @author Erhan CAVDAR
 */
public class ConsumerMapper {
    /**
     * Maps the given {@link ConsumerCreationRequest} object to a persistable
     * {@link Consumer} entity.
     * @param consumerCreationRequest
     * @return the target Consumer object
     */
    public static Consumer getConsumer(ConsumerCreationRequest consumerCreationRequest){
        return Consumer.builder()
                .forename(consumerCreationRequest.getForename())
                .surname(consumerCreationRequest.getSurname())
                .identificationNumber(consumerCreationRequest.getIdentificationNumber())
                .creditScore(consumerCreationRequest.getCreditScore())
                .build();
    }
}
