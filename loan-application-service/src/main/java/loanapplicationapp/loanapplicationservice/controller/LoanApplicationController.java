package loanapplicationapp.loanapplicationservice.controller;

import loanapplicationapp.loanapplicationservice.DTO.request.LoanApplicationRequest;
import loanapplicationapp.loanapplicationservice.utilities.UtilityMethods;
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
        loanApplicationRequest.setForename(UtilityMethods.uppercaseFirstChar(loanApplicationRequest.getForename()));
        loanApplicationRequest.setSurname(UtilityMethods.uppercaseFirstChar(loanApplicationRequest.getSurname()));
        return ResponseEntity.ok(loanApplicationRequest);
    }

}
