package dev.loanapplicationapi.controller;

import dev.loanapplicationapi.DTO.response.EligibilityResponse;
import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.service.LoanApplicationService;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/loan-applications")
@NoArgsConstructor
public class LoanApplicationController {
    LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<EligibilityResponse> postApplication(@RequestBody @Valid LoanApplicationRequest loanApplicationRequest) {
        return ResponseEntity.ok(loanApplicationService.checkEligibility(loanApplicationRequest));
    }

}
