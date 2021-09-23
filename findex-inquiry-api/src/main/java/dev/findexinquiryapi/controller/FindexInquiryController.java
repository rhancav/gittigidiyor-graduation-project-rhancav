package dev.findexinquiryapi.controller;

import dev.findexinquiryapi.DTO.request.ConsumerCreationRequest;
import dev.findexinquiryapi.DTO.response.ScoreInquiryResponse;
import dev.findexinquiryapi.entity.Consumer;
import dev.findexinquiryapi.mappers.ConsumerMapper;
import dev.findexinquiryapi.service.ConsumerService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}
