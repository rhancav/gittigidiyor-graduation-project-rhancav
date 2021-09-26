package dev.loanapplicationui.service.concrete;

import dev.loanapplicationui.DTO.Consumer;
import dev.loanapplicationui.service.ConsumerService;
import dev.loanapplicationui.utilities.StringConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final RestTemplate restTemplate;
    @Override
    public List<Consumer> getConsumers() {
        List<Consumer> consumers = restTemplate.getForEntity(StringConstants.FINDEX_CONSUMERS_API_URI, List.class).getBody();
        log.warn("Consumer list: "+ consumers);
        return consumers;
    }

    @Override
    public ResponseEntity<Consumer> save(Consumer consumer) {
        ResponseEntity<Consumer> consumerResponseEntity = restTemplate.postForEntity(StringConstants.FINDEX_CONSUMERS_API_URI, consumer, Consumer.class);
        return consumerResponseEntity;
    }
}
