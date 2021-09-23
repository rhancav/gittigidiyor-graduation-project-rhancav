package dev.loanapplicationapi.model;

import lombok.*;

import javax.persistence.Embeddable;


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
