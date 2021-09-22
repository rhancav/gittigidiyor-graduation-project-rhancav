package dev.findexinquiryapi.DTO.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Erhan CAVDAR
 */
@Data
@ApiModel(description = "Response model for credit score inquiries.")
public class ScoreInquiryResponse {
    @ApiModelProperty(example = "1400",
            dataType = "Integer"
    )
    private final int score;
    @ApiModelProperty(example = "2750",
            dataType = "Double"
    )
    private final double monthlyIncome;
}
