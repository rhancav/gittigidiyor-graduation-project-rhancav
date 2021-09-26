package dev.smsserviceapi.DTO.response;
/**
 * @author Erhan Cavdar.
 */
public class UnsuccessfulDeliveryResponse extends AbstractResponse {
    public UnsuccessfulDeliveryResponse() {
        super("Ooops, something went wrong.", false);
    }
}
