package dev.findexinquiryservice.controller;

import dev.findexinquiryservice.DTO.request.ConsumerCreationRequest;
import dev.findexinquiryservice.DTO.request.CreditScoreInquiryRequest;
import dev.findexinquiryservice.DTO.response.ScoreInquiryResponse;
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
@RequestMapping("/api/credit-score-inquiry")
@RequiredArgsConstructor
@Slf4j
public class FindexInquiryController {
    private final ConsumerService consumerService;

    /**
     * Calls the getScoreAndIncomeInfoById() method from the {@link ConsumerService} class and wraps the
     * {@link ScoreInquiryResponse} object with {@link ResponseEntity} then returns it.
     * @param creditScoreInquiryRequest contains consumer surname, forname and id.
     * @return {@link ScoreInquiryResponse} object which contains the calculated score and monthly salary.
     */
    @PostMapping
    public ResponseEntity<ScoreInquiryResponse> getCreditScore(@RequestBody @Valid CreditScoreInquiryRequest creditScoreInquiryRequest) {
        return ResponseEntity.ok(consumerService.getCreditScore(creditScoreInquiryRequest));
    }

}
