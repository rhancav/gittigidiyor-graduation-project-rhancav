package dev.findexinquiryservice.controller;

import dev.findexinquiryservice.DTO.request.ConsumerCreationRequest;
import dev.findexinquiryservice.entity.Consumer;
import dev.findexinquiryservice.mappers.ConsumerMapper;
import dev.findexinquiryservice.service.ConsumerService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ConsumerController {
    private final ConsumerService consumerService;

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
    public ResponseEntity<String> update(@PathVariable @ApiParam(example = "35476897812", required = true) long identificationNumber, @RequestBody Consumer consumer){
        log.error("Controller level consumer data is : "+consumer);
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
