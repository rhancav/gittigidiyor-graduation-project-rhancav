package dev.findexinquiryservice.DTO.response;

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
}
