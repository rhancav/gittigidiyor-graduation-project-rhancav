package loanapplicationapp.loanapplicationservice.controller;

import loanapplicationapp.loanapplicationservice.DTO.request.LoanApplicationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/loan-applications")
public class LoanApplicationController {
    @PostMapping
    public ResponseEntity<?> postApplication(@RequestBody @Valid LoanApplicationRequest loanApplicationRequest){
        System.out.println(loanApplicationRequest);
        return ResponseEntity.ok(loanApplicationRequest);
    }
}
