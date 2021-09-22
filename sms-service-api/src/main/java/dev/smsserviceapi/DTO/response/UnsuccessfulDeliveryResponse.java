package dev.smsserviceapi.DTO.response;

public class UnsuccessfulDeliveryResponse extends AbstractResponse{
    public UnsuccessfulDeliveryResponse() {
        super("Ooops, something went wrong.", false);
    }
}
