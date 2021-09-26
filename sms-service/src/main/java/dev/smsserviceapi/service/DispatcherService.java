package dev.smsserviceapi.service;

import dev.smsserviceapi.DTO.request.SMSRequest;

/**
 * Contains SMS service related abstarct methods
 */
public interface DispatcherService {
    /**
     * Does nothing but logging the incoming request to the console,
     * just a mock sms service.
     * @param smsRequest
     */
    void dispatch(SMSRequest smsRequest);
}
