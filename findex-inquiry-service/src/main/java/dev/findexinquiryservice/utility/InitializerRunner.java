package dev.findexinquiryservice.utility;

import dev.findexinquiryservice.entity.Consumer;
import dev.findexinquiryservice.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Implements the {@link CommandLineRunner} interface to
 * insert some mock data at the beggining.
 * @author Erhan CAVDAR
 */
@Component
@RequiredArgsConstructor
public class InitializerRunner implements CommandLineRunner {
    private final ConsumerService consumerService;

    @Override
    public void run(String... args) throws Exception {
        Consumer consumer = Consumer.builder()
                .forename("Osman")
                .surname("Kahraman")
                .identificationNumber(87618281292L)
                .build();
        Consumer consumer2 = Consumer.builder()
                .forename("Ali")
                .surname("Kahraman")
                .identificationNumber(97898281298L)
                .build();
        Consumer consumer3 = Consumer.builder()
                .forename("Hasan")
                .surname("Mehmet")
                .identificationNumber(32318281290L)
                .build();
        Consumer consumer4 = Consumer.builder()
                .forename("Mehmet")
                .surname("Kahraman")
                .identificationNumber(17618281294L)
                .build();
        Consumer consumer5 = Consumer.builder()
                .forename("Hakkı")
                .surname("Kahraman")
                .identificationNumber(27618281296L)
                .build();
        consumerService.save(consumer);
        consumerService.save(consumer2);
        consumerService.save(consumer3);
        consumerService.save(consumer4);
        consumerService.save(consumer5);
    }
}
