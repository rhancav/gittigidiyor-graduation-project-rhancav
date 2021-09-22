package dev.smsserviceapi.service.concrete;

import dev.smsserviceapi.DTO.request.SMSRequest;
import dev.smsserviceapi.service.DispatcherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DispatcherServiceImpl implements DispatcherService {

    @Override
    public void dispatch(SMSRequest smsRequest) {
        log.info("SMS successfully sent.", smsRequest);
    }
}
