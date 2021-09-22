package dev.findexinquiryapi.controller;

import dev.findexinquiryapi.DTO.response.ScoreInquiryResponse;
import dev.findexinquiryapi.entity.Consumer;
import dev.findexinquiryapi.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumers")
@RequiredArgsConstructor
public class FindexInquiryController {
    private final ConsumerService consumerService;

    @GetMapping("/{ID}")
    public ResponseEntity<ScoreInquiryResponse> getCreditScore(@PathVariable long ID) {
        System.out.println(ID);
        return ResponseEntity.ok(consumerService.getScoreAndIncomeInfoByID(ID));
    }

    @PostMapping
    public ResponseEntity<Consumer> save(@RequestBody Consumer consumer) {
        return ResponseEntity.ok(consumerService.save(consumer));
    }

}
