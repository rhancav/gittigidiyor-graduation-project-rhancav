package dev.findexinquiryapi.entity;



import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
