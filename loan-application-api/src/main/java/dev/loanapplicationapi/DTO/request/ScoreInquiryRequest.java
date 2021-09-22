package dev.loanapplicationapi.DTO.request;

import dev.loanapplicationapi.utilities.Messages;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ScoreInquiryRequest {
    @NotNull
    @Valid
    @Digits(integer = 11, fraction = 0, message = Messages.NOT_A_VALID_ID_ERROR)
    @Min(value = 10000000000L, message = Messages.NOT_A_VALID_ID_ERROR)
    private Long identificationNumber;
}
