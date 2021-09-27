package dev.smsservice.controller;

import dev.smsservice.DTO.request.SMSRequest;
import dev.smsservice.DTO.response.AbstractResponse;
import dev.smsservice.DTO.response.SuccessfulDeliveryResponse;
import dev.smsservice.service.DispatcherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sms-dispatcher")
@RequiredArgsConstructor
@Slf4j
public class SMSDispatcher {
    private final DispatcherService dispatcherService;

    /**
     * Dispatches the incoming smsRequest to the fake sms service.
     * @param smsRequest containing message and phone.
     * @return a response containing success status and a message.
     */
    @PostMapping
    public ResponseEntity<AbstractResponse> dispatchSMS(@RequestBody SMSRequest smsRequest) {
        log.info("Got new message: "+smsRequest+". \n Dispatching it to Dispatcher Service.");
        dispatcherService.dispatch(smsRequest);
        return ResponseEntity.ok(new SuccessfulDeliveryResponse());
    }

}
