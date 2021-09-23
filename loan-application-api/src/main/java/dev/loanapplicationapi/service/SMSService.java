package dev.loanapplicationapi.service;

import dev.loanapplicationapi.DTO.request.LoanApplicationRequest;

/**
 * Contains methods related to SMS service which is
 * responsible with managing communication between
 * SMS Service API and Loan Application API.
 * @author Erhan Cavdar
 */
public interface SMSService {
    /**
     * Gets the consumers forename, surname and phonenumber from {@link LoanApplicationRequest} object to create sms message according to
     * status of "eligible" parameter. After generating the message, sends it to the SMSDispatcher Service which is implemented under
     * sms-service-api microservice.
     *
     * @param loanApplicationRequest of the incoming applicaton request
     * @param creditLimit            of the consumer according to their eligibility tier
     * @param eligible               eligibility status
     */
    void sendApplicationNotice(LoanApplicationRequest loanApplicationRequest, double creditLimit, boolean eligible);
}
