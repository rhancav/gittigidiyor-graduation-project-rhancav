package dev.loanapplicationapi.DTO.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SMSRequest {
    private final String phoneNumber;
    private final String message;
    private final LocalDate sentTime = LocalDate.now();
}


