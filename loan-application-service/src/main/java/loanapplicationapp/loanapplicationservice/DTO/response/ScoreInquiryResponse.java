package loanapplicationapp.loanapplicationservice.DTO.response;

import lombok.Data;

@Data
public class ScoreInquiryResponse {
    private final int score;
    private final double monthlyIncome;
}
