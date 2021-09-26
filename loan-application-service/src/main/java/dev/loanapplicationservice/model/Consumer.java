package dev.loanapplicationservice.model;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * Embeddable Consumer object which contains basic data on Consumer.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Embeddable
public class Consumer {
    private Long identificationNumber;
    private String forename;
    private String surname;
    private Double monthlyIncome;
}
