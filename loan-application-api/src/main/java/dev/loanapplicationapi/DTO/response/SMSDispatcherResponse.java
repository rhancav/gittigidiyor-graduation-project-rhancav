package dev.loanapplicationapi.DTO.response;

import lombok.Data;

@Data
public class SMSDispatcherResponse {
    private final String message;
    private final boolean success;
}
