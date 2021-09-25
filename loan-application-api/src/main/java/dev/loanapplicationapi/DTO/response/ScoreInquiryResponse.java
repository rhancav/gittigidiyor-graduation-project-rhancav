package dev.loanapplicationapi.DTO.response;

import dev.loanapplicationapi.DTO.request.ScoreInquiryRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response object for {@link ScoreInquiryRequest}
 * containing only the credit score of the consumer.
 * @author Erhan Cavdar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreInquiryResponse {
    @ApiModelProperty(example = "1900")
    private int score;
}
