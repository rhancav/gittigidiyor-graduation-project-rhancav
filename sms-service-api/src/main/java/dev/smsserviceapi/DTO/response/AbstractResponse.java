package dev.smsserviceapi.DTO.response;

import lombok.Data;

@Data
public abstract class AbstractResponse {
    private final String message;
    private final boolean success;
}
