package dev.smsserviceapi.DTO.response;

public class SuccessfulDeliveryResponse extends AbstractResponse{
    public SuccessfulDeliveryResponse() {
        super("SMS sent successfully.", true);
    }
}
