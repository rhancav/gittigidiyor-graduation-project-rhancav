package dev.smsserviceapi.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SMSRequest {
    private final String phoneNumber;
    private final String message;
    @JsonIgnore
    private final LocalDate sentTime = LocalDate.now();
}
