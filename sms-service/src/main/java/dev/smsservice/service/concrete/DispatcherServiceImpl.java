package dev.smsservice.service.concrete;

import dev.smsservice.DTO.request.SMSRequest;
import dev.smsservice.service.DispatcherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation class for the Dispatcher Service interface.
 * @author Erhan Cavdar
 */
@Service
@Slf4j
public class DispatcherServiceImpl implements DispatcherService {
    /**
     * {@inheritDoc}
     * @param smsRequest
     */
    @Override
    public void dispatch(SMSRequest smsRequest) {
        log.info("SMS successfully sent.", smsRequest);
    }
}
