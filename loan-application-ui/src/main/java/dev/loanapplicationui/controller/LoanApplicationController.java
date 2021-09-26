package dev.loanapplicationui.controller;

import dev.loanapplicationui.DTO.request.LoanApplicationRequest;
import dev.loanapplicationui.DTO.response.CreditEligibilityResponse;
import dev.loanapplicationui.service.LoanApplicationService;
import dev.loanapplicationui.utilities.UtilityMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;

    @GetMapping
    public String mainPage(){
        return "index";
    }

    @PostMapping("/applications")
    public String postApplication(@ModelAttribute LoanApplicationRequest loanApplicationRequest, Model model, BindingResult bindingResult) {
        log.warn(loanApplicationRequest.toString());
        CreditEligibilityResponse creditEligibilityResponse = loanApplicationService.postApplication(loanApplicationRequest);
        model.addAttribute("result", creditEligibilityResponse.getEligibility());
        model.addAttribute("message", creditEligibilityResponse.getMessage());
        return "application-result-page";
    }

    @GetMapping("/new-application")
    public String newApplication(@ModelAttribute @Valid LoanApplicationRequest loanApplicationRequest, Model model, BindingResult bindingResult){
        UtilityMethods.logErrors(bindingResult);
        model.addAttribute(loanApplicationRequest);
        return "new-application-page";
    }

    @GetMapping("/applications")
    public String appList(Model model, @RequestParam long identificationNumber){
        model.addAttribute("identificationNumber", identificationNumber);
        model.addAttribute("logs", loanApplicationService.getLogsByID(identificationNumber));
        return "application-page";
    }

    @GetMapping("/application-inquiry")
    public String handleInquiry(){
        return "application-inquiry-page";
    }
}
