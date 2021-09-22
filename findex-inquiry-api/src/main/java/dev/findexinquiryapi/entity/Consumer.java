package dev.findexinquiryapi.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity model for Consumer data.
 * @author Erhan CAVDAR
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long identificationNumber;
    private String forename;
    private String surname;
    private Double monthlyIncome;
    private int creditScore;
}
