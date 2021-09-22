package dev.loanapplicationapi.controller;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.DTO.response.EligibilityResponse;
import dev.loanapplicationapi.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/loan-applications")
@RequiredArgsConstructor
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<EligibilityResponse> postApplication(@RequestBody @Valid LoanApplicationRequest loanApplicationRequest) {
        return ResponseEntity.ok(loanApplicationService.checkEligibility(loanApplicationRequest));
    }

}
