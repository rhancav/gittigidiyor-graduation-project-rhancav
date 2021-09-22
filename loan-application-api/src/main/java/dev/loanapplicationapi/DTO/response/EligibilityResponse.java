package dev.loanapplicationapi.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EligibilityResponse {
    private EligibilityStatus eligibilityStatus;
    private String message;
}
