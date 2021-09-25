package dev.loanapplicationapi.controller;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;
import dev.loanapplicationapi.DTO.response.EligibilityResponse;
import dev.loanapplicationapi.model.CreditApplicationLog;
import dev.loanapplicationapi.service.CreditApplicationLogService;
import dev.loanapplicationapi.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/loan-applications")
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;
    private final CreditApplicationLogService creditApplicationLogService;

    @PostMapping
    public ResponseEntity<EligibilityResponse> postApplication(@RequestBody @Valid LoanApplicationRequest loanApplicationRequest) {
        return ResponseEntity.ok(loanApplicationService.checkEligibility(loanApplicationRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<CreditApplicationLog>> findApplicationsByID(@PathVariable long id, @RequestParam(required = false, defaultValue = "UNFILTERED") String filter){
        log.warn("ID of the consumer is: "+id);
        log.warn("Given filter : "+filter);
        return ResponseEntity.ok(creditApplicationLogService.findAllByID(id, filter));
    }

}
