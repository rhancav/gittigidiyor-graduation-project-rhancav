package dev.loanapplicationui.DTO;


import dev.loanapplicationui.utilities.Messages;
import dev.loanapplicationui.utilities.StringConstants;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

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
    @NotNull
    @Digits(integer = 11, fraction = 0, message = Messages.NOT_A_VALID_ID_ERROR)
    @Min(value = 10000000000L, message = Messages.NOT_A_VALID_ID_ERROR)
    private Long identificationNumber;
    @NotBlank
    @Pattern(regexp = StringConstants.FORENAME_REGEX, message = Messages.NON_ALPHABETICAL_ERROR)
    private String forename;
    @NotBlank
    @Pattern(regexp = StringConstants.SURNAME_REGEX, message = Messages.NON_ALPHABETICAL_ERROR)
    private String surname;
    @NotNull
    @Digits(integer = 4, fraction = 0, message = Messages.SCORE_LENGTH_ERROR)
    @Positive(message = Messages.POSITIVE_NUMBER_ERROR)
    private int creditScore;
    @Digits(integer = 4, fraction = 0, message = Messages.SCORE_LENGTH_ERROR)
    @Positive(message = Messages.POSITIVE_NUMBER_ERROR)
    private Double monthlyIncome;
}
