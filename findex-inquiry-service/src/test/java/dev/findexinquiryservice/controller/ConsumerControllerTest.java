package dev.findexinquiryservice.controller;

import dev.findexinquiryservice.DTO.request.ConsumerCreationRequest;
import dev.findexinquiryservice.entity.Consumer;
import dev.findexinquiryservice.exceptions.ConsumerAlreadyExistsException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ConsumerControllerTest {

    @Mock
    ConsumerService consumerService;
    @InjectMocks
    ConsumerController consumerController;
    Consumer consumer;
    ConsumerCreationRequest consumerCreationRequest;
    List<Consumer> consumerList;

    @BeforeEach
    void setUp() {
        consumerCreationRequest = ConsumerCreationRequest.builder()
                .forename("Erhan")
                .surname("Cavdar")
                .identificationNumber(34756191873L)
                .creditScore(1400)
                .build();
        consumer = ConsumerMapper.getConsumer(consumerCreationRequest);
        consumer.setId(1L);
        consumerList = new ArrayList<>();
        consumerList.add(consumer);
    }


    @AfterEach
    void tearDown() {
        consumer = null;
        consumerList.clear();
    }



    @Test
    void should_return_response_entity_with_200_ok_when_save(){
        // Given
        when(consumerService.save(any())).thenReturn(consumer);
        // When
        ResponseEntity<Consumer> responseEntity = consumerController.save(consumerCreationRequest);
        // Then
        assertAll(
                ()-> assertEquals(responseEntity.getBody().getIdentificationNumber(), consumerCreationRequest.getIdentificationNumber()),
                ()-> assertEquals(responseEntity.getStatusCodeValue(), 200)
        );
    }
    @Test
    void should_return_response_entity_with_200_ok_when_delete(){
        // When
        ResponseEntity<String> responseEntity = consumerController.deleteByID(consumer.getIdentificationNumber());
        // Then
        assertAll(
                ()-> assertEquals(responseEntity.getStatusCodeValue(), 200)
        );
    }

    @Test
    void should_return_response_entity_with_200_ok_when_update(){
        // When
        ResponseEntity<String> responseEntity = consumerController.update(consumer.getIdentificationNumber(), consumer);
        // Then
        assertAll(
                ()-> assertEquals(responseEntity.getStatusCodeValue(), 200)
        );
    }

    @Test
    void should_return_consumer_list_when_find_all_called(){
        // Given
        when(consumerService.findAll()).thenReturn(consumerList);
        // When
        ResponseEntity<List<Consumer>> actual = consumerController.findAll();
        // Then
        assertAll(
                () -> assertEquals(actual.getStatusCodeValue(), 200),
                () -> assertFalse(actual.getBody().isEmpty())
        );
    }

    @Test
    void should_throw_already_exists_exception_when_save(){
        // Given
        when(consumerService.save(any())).thenThrow(ConsumerAlreadyExistsException.class);

        // Then
        assertThrows(ConsumerAlreadyExistsException.class, ()->consumerController.save(consumerCreationRequest));
    }

}
