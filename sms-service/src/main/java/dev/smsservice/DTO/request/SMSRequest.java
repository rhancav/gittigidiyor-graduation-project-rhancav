package dev.smsservice.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(description = "Mock sms request")
public class SMSRequest {
    @ApiModelProperty(example = "05549078345", required = true)
    private final String phoneNumber;
    @ApiModelProperty(example = "Your credit application is approved.", required = true)
    private final String message;
    // Send time
    @JsonIgnore
    private final LocalDate sentTime = LocalDate.now();
}
