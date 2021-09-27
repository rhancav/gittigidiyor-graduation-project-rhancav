package dev.loanapplicationui.DTO;

import lombok.*;

import java.time.Instant;


/**
 * Credit Application Log which contains date, result, limit and consumer info.
 * It is not updatable.
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationLog {
    private Consumer consumer;
    private String result;
    private Double creditLimit;
    private Instant creationTime;
}
