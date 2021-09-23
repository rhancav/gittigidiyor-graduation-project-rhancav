package dev.loanapplicationapi.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Immutable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Immutable // Dont see any reason to update a log? If you do, let me now.
public class CreditApplicationLog extends AbstractEntity{
    @Embedded
    private Consumer consumer;
    @NotBlank
    private String result;
    private Double creditLimit;
}
