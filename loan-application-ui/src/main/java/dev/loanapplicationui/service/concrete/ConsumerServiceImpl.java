package dev.loanapplicationui.service.concrete;

import dev.loanapplicationui.DTO.Consumer;
import dev.loanapplicationui.service.ConsumerService;
import dev.loanapplicationui.utilities.StringConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final RestTemplate restTemplate;
    @Value("${findex.consumers.service.uri}")
    public String FINDEX_CONSUMERS_API_URI;
    @Override
    public List<Consumer> getConsumers() {
        List<Consumer> consumers = restTemplate.getForEntity(FINDEX_CONSUMERS_API_URI, List.class).getBody();
        log.warn("Consumer list: "+ consumers);
        return consumers;
    }

    @Override
    public ResponseEntity<Consumer> save(Consumer consumer) {
        ResponseEntity<Consumer> consumerResponseEntity = restTemplate.postForEntity(FINDEX_CONSUMERS_API_URI, consumer, Consumer.class);
        return consumerResponseEntity;
    }

    @Override
    public void delete(long identificationNumber) {
        restTemplate.delete(FINDEX_CONSUMERS_API_URI+"/"+identificationNumber);
    }

    @Override
    public void update(Consumer consumer) {
        log.error("Service level consumer data: "+consumer.toString());
        restTemplate.put(FINDEX_CONSUMERS_API_URI+"/"+consumer.getIdentificationNumber(), consumer);
    }
}
