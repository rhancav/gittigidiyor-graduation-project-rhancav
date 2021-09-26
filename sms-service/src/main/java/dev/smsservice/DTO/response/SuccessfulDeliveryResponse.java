package dev.smsservice.DTO.response;

/**
 * @author Erhan Cavdar.
 */
public class SuccessfulDeliveryResponse extends AbstractResponse {
    public SuccessfulDeliveryResponse() {
        super("SMS sent successfully.", true);
    }
}
