package dev.findexinquiryapi.service;

import dev.findexinquiryapi.DTO.response.ScoreInquiryResponse;
import dev.findexinquiryapi.entity.Consumer;

import java.util.List;

public interface ConsumerService {
    Consumer save(Consumer consumer);
    void update(Consumer consumer);
    void delete(Long identificationNumber);
    List<Consumer> findAll();
    Consumer findByIdentificationNumber(Long identificationNumber);
    ScoreInquiryResponse getScoreAndIncomeInfoByID(Long identificationNumber);
}
