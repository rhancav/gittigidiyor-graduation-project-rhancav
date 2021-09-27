package dev.findexinquiryservice.DTO.request;

import dev.findexinquiryservice.utility.Messages;
import dev.findexinquiryservice.utility.StringConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author Erhan CAVDAR
 */
@Data
@ApiModel(description = "Simple POJO to separate Consumer entity from the control layer while saving a new consumer.")
public class ConsumerCreationRequest {
    @NotNull
    @Digits(integer = 11, fraction = 0, message = Messages.NOT_A_VALID_ID_ERROR)
    @Min(value = 10000000000L, message = Messages.NOT_A_VALID_ID_ERROR)
    @ApiModelProperty(example = "15216215276",
            required = true,
            dataType = "Long",
            notes = "Should not exceed 11 digits and cannot be lower then 11 digits long.Last digit cannot be an odd number."
    )
    private Long identificationNumber;
    @NotBlank
    @Pattern(regexp = StringConstants.FORENAME_REGEX, message = Messages.NON_ALPHABETICAL_ERROR)
    @ApiModelProperty(example = "Osman",
            required = true,
            dataType = "String",
            notes = "It is UNICODE supported. Should be at least 3, maximum 30 chars long."
    )
    private String forename;
    @NotBlank
    @Pattern(regexp = StringConstants.SURNAME_REGEX, message = Messages.NON_ALPHABETICAL_ERROR)
    @ApiModelProperty(example = "Akca",
            required = true,
            dataType = "String",
            notes = "It is UNICODE supported. Should be at least 2, maximum 30 chars long."
    )
    private String surname;
    @Digits(integer = 4, fraction = 0, message = Messages.SCORE_LENGTH_ERROR)
    @Min(value = 0, message = Messages.POSITIVE_NUMBER_ERROR)
    @ApiModelProperty(example = "500",
            required = false,
            dataType = "Integer",
            notes = "If not given will be automatically set according to national ids last digit. Should be 4 digits long max."
    )
    private int creditScore;
}
