package dev.loanapplicationservice.service.concrete;

import dev.loanapplicationservice.DTO.request.LoanApplicationRequest;
import dev.loanapplicationservice.DTO.response.EligibilityResponse;
import dev.loanapplicationservice.DTO.response.ScoreInquiryResponse;
import dev.loanapplicationservice.exceptions.NotAValidIDException;
import dev.loanapplicationservice.exceptions.NotNullableException;
import dev.loanapplicationservice.service.CreditApplicationLogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceImplTest {
    @InjectMocks
    LoanApplicationServiceImpl loanApplicationService;
    @Mock
    RestTemplate restTemplate;
    LoanApplicationRequest loanApplicationRequest;
    ScoreInquiryResponse scoreInquiryResponse;

    @BeforeEach
    void setUp() {
        loanApplicationRequest = LoanApplicationRequest.builder()
                .identificationNumber(12312312318L)
                .forename("Erhan")
                .forename("Cavdar")
                .phone("5549078090")
                .build();
        scoreInquiryResponse = new ScoreInquiryResponse();
        scoreInquiryResponse.setScore(1500);
    }

    @AfterEach
    void tearDown() {
        loanApplicationRequest = null;
    }
    @Test
    void should_throw_not_a_valid_id(){
        // Id ends with odd number
      assertThrows(NotAValidIDException.class, ()-> loanApplicationService.checkEligibility(loanApplicationRequest));
    }

    @Test
    void should_throw_not_nullable_exception(){
        assertThrows(NotNullableException.class, ()-> loanApplicationService.checkEligibility(null));
    }

}
