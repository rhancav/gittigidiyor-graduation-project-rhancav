package dev.loanapplicationui.service;

import dev.loanapplicationui.DTO.Consumer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConsumerService {
    List<Consumer> getConsumers();
    ResponseEntity<Consumer> save(Consumer consumer);
    void delete(long identificationNumber);
    void update(Consumer consumer);
}
