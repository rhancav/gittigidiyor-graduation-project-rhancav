package dev.loanapplicationservice.controller;

import dev.loanapplicationservice.DTO.request.LoanApplicationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class LoanApplicationControllerTest {

    @Test
    void Should_Throw_Validation_Exception() {
        LoanApplicationRequest loanApplicationRequest = LoanApplicationRequest.builder().forename("E1").build();
        // TODO
    }
}
