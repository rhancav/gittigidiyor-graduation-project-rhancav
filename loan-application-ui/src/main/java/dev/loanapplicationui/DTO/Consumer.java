package dev.loanapplicationui.DTO;


import lombok.*;

/**
 * DTO model for Consumer data.
 * @author Erhan CAVDAR
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
    private Long identificationNumber;
    private String forename;
    private String surname;
    private int creditScore;
    private Double monthlyIncome;
}
