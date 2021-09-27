package dev.findexinquiryservice.controller;

import dev.findexinquiryservice.DTO.request.ConsumerCreationRequest;
import dev.findexinquiryservice.DTO.request.CreditScoreInquiryRequest;
import dev.findexinquiryservice.DTO.response.ScoreInquiryResponse;
import dev.findexinquiryservice.entity.Consumer;
import dev.findexinquiryservice.exceptions.ConsumerNotExistentException;
import dev.findexinquiryservice.mappers.ConsumerMapper;
import dev.findexinquiryservice.service.ConsumerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class FindexInquiryControllerTest {
    @InjectMocks
    FindexInquiryController findexInquiryController;
    @Mock
    ConsumerService consumerService;
    ScoreInquiryResponse scoreInquiryResponse;
    CreditScoreInquiryRequest creditScoreInquiryRequest;
    @BeforeEach
    void setUp() {
     scoreInquiryResponse = new ScoreInquiryResponse(1200);
     creditScoreInquiryRequest = new CreditScoreInquiryRequest("Erhan", "Cavdar", 12312312312L);
    }


    @AfterEach
    void tearDown() {
       scoreInquiryResponse = null;
       creditScoreInquiryRequest = null;
    }
    @Test
    void should_return_response_entity_with_200_ok_when_save(){
        // Given
        when(consumerService.getCreditScore(any())).thenReturn(scoreInquiryResponse);
        // When
        ResponseEntity<ScoreInquiryResponse> actual = findexInquiryController.getCreditScore(creditScoreInquiryRequest);
        // Then
        assertAll(
                () -> assertEquals(actual.getStatusCodeValue(), 200),
                () -> assertEquals(actual.getBody().getScore(), 1200)
        );
    }

    @Test
    void should_throw_not_existent_when_save(){
        // Given
        when(consumerService.getCreditScore(any())).thenThrow(ConsumerNotExistentException.class);
        // Then
        assertThrows(ConsumerNotExistentException.class, ()-> consumerService.getCreditScore(creditScoreInquiryRequest));
    }

}
