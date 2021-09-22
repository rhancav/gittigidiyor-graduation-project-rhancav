package loanapplicationapp.loanapplicationservice.controller;

import loanapplicationapp.loanapplicationservice.DTO.request.LoanApplicationRequest;
import loanapplicationapp.loanapplicationservice.DTO.response.EligibilityResponse;
import loanapplicationapp.loanapplicationservice.service.LoanApplicationService;
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
