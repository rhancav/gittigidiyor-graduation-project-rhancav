package dev.findexinquiryservice.service.concrete;

import dev.findexinquiryservice.DTO.request.ConsumerCreationRequest;
import dev.findexinquiryservice.DTO.request.CreditScoreInquiryRequest;
import dev.findexinquiryservice.DTO.response.ScoreInquiryResponse;
import dev.findexinquiryservice.entity.Consumer;
import dev.findexinquiryservice.exceptions.ConsumerAlreadyExistsException;
import dev.findexinquiryservice.exceptions.ConsumerNotExistentException;
import dev.findexinquiryservice.repository.ConsumerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ConsumerServiceImplTest {
    @InjectMocks
    ConsumerServiceImpl consumerService;
    @Mock
    ConsumerRepository consumerRepository;
    Consumer consumer;
    ScoreInquiryResponse scoreInquiryResponse;
    CreditScoreInquiryRequest creditScoreInquiryRequest;
    List<Consumer> consumerList;

    @BeforeEach
    void setUp() {
        scoreInquiryResponse = new ScoreInquiryResponse(1400);
        creditScoreInquiryRequest = new CreditScoreInquiryRequest("Erhan", "Cavdar", 35535435434l);
        consumer = Consumer.builder()
                .forename("Erhan")
                .surname("Cavdar")
                .identificationNumber(34756191873L)
                .creditScore(1400)
                .id(1L)
                .build();
        consumerList = new ArrayList<>();
        consumerList.add(consumer);
    }

    @AfterEach
    void tearDown() {
        consumer = null;
        consumerList.clear();
    }
    @Test
    void should_return_550_when_calculate_score_called_with_12312312312(){
        int score = consumerService.calculateScore(12312312312L);
        assertEquals(score, 550);
    }

    @Test
    void should_return_consumer_when_save(){
        // Given
        when(consumerRepository.save(any())).thenReturn(consumer);
        // When
        Consumer actual = consumerService.save(consumer);
        // Then
        assertAll(
                ()-> assertEquals(actual.getIdentificationNumber(), consumer.getIdentificationNumber()),
                () -> assertEquals(actual.getForename(), consumer.getForename())
        );
    }
    @Test
    void should_return_consumer_list(){
        // Given
        when(consumerRepository.findAll()).thenReturn(consumerList);
        // When
        List<Consumer> actual = consumerService.findAll();
        // Then
        assertAll(
                ()-> assertFalse(actual.isEmpty()),
                () -> assertEquals(actual.size(), 1)
        );
    }
    @Test
    void should_return_consumer_when_find_by_id(){
        // Given
        when(consumerRepository.findConsumerByIdentificationNumber(anyLong())).thenReturn(Optional.of(consumer));
        // When
        Consumer actual = consumerService.findByIdentificationNumber(12312312312L);
        // Then
        assertAll(
                ()-> assertEquals(actual.getIdentificationNumber(), consumer.getIdentificationNumber())
        );
    }


    @Test
    void should_throw_already_exists_when_save(){
        // Given
        when(consumerRepository.findConsumerByIdentificationNumber(anyLong())).thenReturn(Optional.of(consumer));
        // Then
        assertThrows(ConsumerAlreadyExistsException.class, () -> consumerService.save(consumer));

    }
    @Test
    void should_throw_not_existent_when_get_credit_score_and_consumer_not_found(){
        // Given
        when(consumerRepository.existsByForenameAndSurnameAndIdentificationNumber(anyString(), anyString(), anyLong())).thenReturn(false);
        // Then
        assertThrows(ConsumerNotExistentException.class, ()-> consumerService.getCreditScore(creditScoreInquiryRequest));
    }
}
