package dev.loanapplicationui.controller;

import dev.loanapplicationui.DTO.CreditApplicationLog;
import dev.loanapplicationui.DTO.request.LoanApplicationRequest;
import dev.loanapplicationui.DTO.response.CreditEligibilityResponse;
import dev.loanapplicationui.service.LoanApplicationService;
import dev.loanapplicationui.utilities.UtilityMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;


    @PostMapping("/applications")
    public String postApplication(@Valid LoanApplicationRequest loanApplicationRequest, Model model) {
        log.warn(loanApplicationRequest.toString());
        CreditEligibilityResponse creditEligibilityResponse;
        try{
            creditEligibilityResponse = loanApplicationService.postApplication(loanApplicationRequest);
        }
        catch (HttpClientErrorException e){
            // Bad practice but works for now
            if(e.getMessage().contains("Consumer information does not match any entity in database.")){
                log.error(e.getMessage());
                return "/error/no-consumer-found-page";
            }
            else{
                return "/error/something-went-wrong-page";
            }
        }
        model.addAttribute("result", creditEligibilityResponse.getEligibility());
        model.addAttribute("message", creditEligibilityResponse.getMessage());
        return "application-result-page";
    }

    @GetMapping("/new-application")
    public String newApplicationForm(@ModelAttribute(name = "loanApplicationRequest") LoanApplicationRequest loanApplicationRequest){
        return "new-application-page";
    }

    @GetMapping("/applications")
    public String applicationList(Model model, @RequestParam long identificationNumber, @RequestParam String filter){
        List<CreditApplicationLog> logs = loanApplicationService.getLogsByID(identificationNumber, filter);
        if(logs.isEmpty()){
            return "/error/no-logs-found-page";
        }
        model.addAttribute("identificationNumber", identificationNumber);
        model.addAttribute("filter", filter);
        model.addAttribute("logs", logs);
        return "application-page";
    }

    @GetMapping("/application-inquiry")
    public String inquiryForm(){
        return "application-inquiry-page";
    }
}
