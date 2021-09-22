package dev.findexinquiryapi.DTO.response;

import dev.findexinquiryapi.exceptions.GlobalHandler;
import lombok.Data;

import java.util.Map;
/**
 * For better presentation of validation errors which
 * are handled by {@link GlobalHandler} class.
 */
@Data
public class ValidationErrorResponse {
    private final int status;
    private final Map<String, String> errors;
}
