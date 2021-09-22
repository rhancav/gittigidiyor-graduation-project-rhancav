package dev.smsserviceapi.service;

import dev.smsserviceapi.DTO.request.SMSRequest;

public interface DispatcherService {
    void dispatch(SMSRequest smsRequest);
}
