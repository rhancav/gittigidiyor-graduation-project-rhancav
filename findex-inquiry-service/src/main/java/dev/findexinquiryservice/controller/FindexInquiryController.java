package dev.findexinquiryservice.controller;

import dev.findexinquiryservice.DTO.request.ConsumerCreationRequest;
import dev.findexinquiryservice.DTO.response.ScoreInquiryResponse;
import dev.findexinquiryservice.entity.Consumer;
import dev.findexinquiryservice.mappers.ConsumerMapper;
import dev.findexinquiryservice.service.ConsumerService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Base controller class for dispatching incoming requests
 * related to consumers.
 * @author Erhan CAVDAR
 */
@RestController
@RequestMapping("/api/consumers")
@RequiredArgsConstructor
public class FindexInquiryController {
    private final ConsumerService consumerService;

    /**
     * Calls the getScoreAndIncomeInfoById() method from the {@link ConsumerService} class and wraps the
     * {@link ScoreInquiryResponse} object with {@link ResponseEntity} then returns it.
     * @param ID national ID of the consumer
     * @return {@link ScoreInquiryResponse} object which contains the calculated score and monthly salary.
     */
    @GetMapping("/{ID}")
    public ResponseEntity<ScoreInquiryResponse> getCreditScore(@PathVariable @ApiParam(example = "45612398712") long ID) {
        return ResponseEntity.ok(consumerService.getScoreByID(ID));
    }

    /**
     * Calls the save method() from he {@link ConsumerService} class and wraps the
     * {@link Consumer} object with {@link ResponseEntity} then returns it.
     * @param consumerCreationRequest request DTO
     * @return {@link Consumer} object wrapped inside {@link ResponseEntity}
     */
    @PostMapping
    public ResponseEntity<Consumer> save(@RequestBody @Valid ConsumerCreationRequest consumerCreationRequest) {
        // Mapped to Consumer object using a custom mapper
        return ResponseEntity.ok(consumerService.save(ConsumerMapper.getConsumer(consumerCreationRequest)));
    }

    /**
     * Lists all of the available consumers without any filters.
     * @return the list of consumers.
     */
    @GetMapping
    public ResponseEntity<List<Consumer>> findAll(){
        return ResponseEntity.ok(consumerService.findAll());
    }

    /**
     * Updates the consumer associated with the given id
     */
    @PutMapping("/{identificationNumber}")
    public ResponseEntity<String> update(@PathVariable @ApiParam(example = "35476897812", required = true) long identificationNumber, @Valid Consumer consumer){
        consumerService.update(identificationNumber, consumer);
        return ResponseEntity.ok(String.format("Consumer with the %d ID is successfully updated.", identificationNumber));
    }

 /*   *//**
     * Get the consumer with the given identification number.
     * @param identificationNumber identification number of the consumer
     * @return the consumer object wrapped insie ResonseEntity object.
     *//*
    @GetMapping("/{identificationNumber}")
    public ResponseEntity<Consumer> getConsumerByID(@PathVariable @ApiParam(example = "35476897812", required = true) long identificationNumber){
        return ResponseEntity.ok(consumerService.findByIdentificationNumber(identificationNumber));
    }*/

    /**
     * Delete the customer with the given identification number.
     * @param identificationNumber identification number of the consumer
     * @return deletion success message if the consumer is existent
     */
    @DeleteMapping("/{identificationNumber}")
    public ResponseEntity<String> deleteByID(@PathVariable @ApiParam(example = "35476897812", required = true) long identificationNumber){
        consumerService.delete(identificationNumber);
        return ResponseEntity.ok(String.format(String.format("Consumer with the %d ID is successfully deleted.", identificationNumber)));
    }


}
