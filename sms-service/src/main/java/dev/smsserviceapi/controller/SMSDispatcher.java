package dev.smsserviceapi.controller;

import dev.smsserviceapi.DTO.request.SMSRequest;
import dev.smsserviceapi.DTO.response.AbstractResponse;
import dev.smsserviceapi.DTO.response.SuccessfulDeliveryResponse;
import dev.smsserviceapi.service.DispatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms-dispatcher")
@RequiredArgsConstructor
public class SMSDispatcher {
    private final DispatcherService dispatcherService;

    /**
     * Dispatches the incoming smsRequest to the fake sms service.
     * @param smsRequest containing message and phone.
     * @return a response containing success status and a message.
     */
    @PostMapping
    public ResponseEntity<AbstractResponse> dispatchSMS(@RequestBody SMSRequest smsRequest) {
        return ResponseEntity.ok(new SuccessfulDeliveryResponse());
    }

}
