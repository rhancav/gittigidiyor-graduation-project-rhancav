package dev.findexinquiryapi.service.concrete;

import dev.findexinquiryapi.DTO.response.ScoreInquiryResponse;
import dev.findexinquiryapi.entity.Consumer;
import dev.findexinquiryapi.exceptions.ConsumerAlreadyExistsException;
import dev.findexinquiryapi.exceptions.ConsumerNotExistentException;
import dev.findexinquiryapi.exceptions.NotAValidIDException;
import dev.findexinquiryapi.repository.ConsumerRepository;
import dev.findexinquiryapi.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    ConsumerRepository consumerRepository;

    @Override
    public Consumer save(Consumer consumer) {
        if(consumerExists(consumer.getIdentificationNumber())){
            throw new ConsumerAlreadyExistsException("");
        }
        consumer.setCreditScore(calculateScore(consumer.getIdentificationNumber()));
        return consumerRepository.save(consumer);
    }

    @Override
    public void update(Consumer consumer) {
        if(!consumerExists(consumer.getIdentificationNumber())){
            throw new ConsumerNotExistentException("");
        }
        consumerRepository.save(consumer);
    }

    @Override
    public void delete(Long identificationNumber) {
        if(!consumerExists(identificationNumber)){
            throw new ConsumerNotExistentException("");
        }
        consumerRepository.deleteByIdentificationNumber(identificationNumber));
    }

    @Override
    public List<Consumer> findAll() {
        return consumerRepository.findAll();
    }

    @Override
    public Consumer findByIdentificationNumber(Long identificationNumber) {
        return consumerRepository.findConsumerByIdentificationNumber(identificationNumber).orElseThrow(()-> new ConsumerNotExistentException(""));
    }

    @Override
    public ScoreInquiryResponse getScoreAndIncomeInfoByID(Long identificationNumber) {
        Consumer consumer = findByIdentificationNumber(identificationNumber);
        ScoreInquiryResponse scoreInquiryResponse = new ScoreInquiryResponse(consumer.getCreditScore(),consumer.getMonthlyIncome());
        return scoreInquiryResponse;
    }

    /**
     * Utility method to calculate credit score by the given
     * national id's last number. We are assuming that the national id
     * cannot end with a odd number. If not valid, throws {@link NotAValidIDException} exception.
     * @param identificationNumber of the consumer
     * @return 550 if id ends with "2",
     * 1000 if id ends with "4",
     * 400 if id ends with "6",
     * 900 if id ends with "8" and 2000 if the id ends with "0".
     */
    private int calculateScore(Long identificationNumber){
        int score;
        if(String.valueOf(identificationNumber).endsWith("2")){
            score = 550;
        }
        else if(String.valueOf(identificationNumber).endsWith("4")){
            score = 1000;
        }
        else if(String.valueOf(identificationNumber).endsWith("6")){
            score = 400;
        }
        else if(String.valueOf(identificationNumber).endsWith("8")){
            score = 900;
        }
        else if (String.valueOf(identificationNumber).endsWith("0")){
            score = 2000;
        }
        else{
            throw new NotAValidIDException("");
        }
        return score;

    }

    /**
     * Checks if the consumer exists or notç
     * @param identificationNumber of the consumer
     * @return true if it exists or else false.
     */
    private boolean consumerExists(Long identificationNumber){
        return consumerRepository.findConsumerByIdentificationNumber(identificationNumber).isPresent();
    }
}
