package dev.loanapplicationservice.controller;

import dev.loanapplicationservice.DTO.request.LoanApplicationRequest;
import dev.loanapplicationservice.DTO.response.EligibilityResponse;
import dev.loanapplicationservice.service.concrete.LoanApplicationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class LoanApplicationControllerTest {
    @InjectMocks
    LoanApplicationController loanApplicationController;
    @Mock
    LoanApplicationServiceImpl loanApplicationService;
    EligibilityResponse eligibilityResponse;

    @BeforeEach
    void setUp() {
        eligibilityResponse = new EligibilityResponse();
        eligibilityResponse.setEligibility("Not Eligible");
        eligibilityResponse.setMessage("Bla bla");
    }

    @AfterEach
    void tearDown() {
        eligibilityResponse = null;
    }
    @Test
    void should_return_eligible_response(){
        // Given
        when(loanApplicationService.checkEligibility(any())).thenReturn(eligibilityResponse);
        // When
        ResponseEntity<EligibilityResponse> actual = loanApplicationController.postApplication(null);
        // Then
        assertAll(
                ()-> assertEquals(actual.getStatusCodeValue(), 200),
                () -> assertEquals(actual.getBody().getEligibility(), "Not Eligible")
        );
    }
}
