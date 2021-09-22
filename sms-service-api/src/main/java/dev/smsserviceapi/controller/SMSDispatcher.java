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

    @PostMapping
    public ResponseEntity<AbstractResponse> dispatchSMS(@RequestBody SMSRequest smsRequest) {
        return ResponseEntity.ok(new SuccessfulDeliveryResponse());
    }

}
