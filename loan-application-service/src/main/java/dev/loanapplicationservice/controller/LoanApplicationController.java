package dev.loanapplicationservice.controller;

import dev.loanapplicationservice.DTO.request.LoanApplicationRequest;
import dev.loanapplicationservice.DTO.response.EligibilityResponse;
import dev.loanapplicationservice.model.CreditApplicationLog;
import dev.loanapplicationservice.service.CreditApplicationLogService;
import dev.loanapplicationservice.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * Base controller class for handling incoming application requests.
 * @author Erhan CAVDAR
 */
@RestController
@RequestMapping("/api/loan-applications")
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationController {
    private final LoanApplicationService loanApplicationService;
    private final CreditApplicationLogService creditApplicationLogService;

    /**
     * New credit application request.
     * @param loanApplicationRequest containing first name, last name, phone and the salary info of the user
     * @return a eligibility response
     */
    @PostMapping
    public ResponseEntity<EligibilityResponse> postApplication(@RequestBody @Valid LoanApplicationRequest loanApplicationRequest) {
        return ResponseEntity.ok(loanApplicationService.checkEligibility(loanApplicationRequest));
    }

    /**
     * Returns all the past applications related to the owner of the given national ID.
     * It is sorted by its creation date and can be filtered as ASC or DESC. If "LAST"
     * is passed as filter value, it returns the latest application. The default value
     * for filter is "UNFILTERED" which returns all the applications.
     * @param id of the applicant.
     * @param filter to apply to the listing.
     * @return the list of applications.
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<CreditApplicationLog>> findApplicationsByID(@PathVariable long id, @RequestParam(required = false, defaultValue = "UNFILTERED") String filter){
        log.warn("ID of the consumer is: "+id);
        log.warn("Given filter : "+filter);
        return ResponseEntity.ok(creditApplicationLogService.findAllByID(id, filter));
    }

}
