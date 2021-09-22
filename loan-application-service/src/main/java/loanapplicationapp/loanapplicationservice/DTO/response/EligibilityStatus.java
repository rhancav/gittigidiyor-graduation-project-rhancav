package loanapplicationapp.loanapplicationservice.DTO.response;

public enum EligibilityStatus {
    ELIGIBLE("Eligible"),
    NOT_ELIGIBLE("Not eligible");
    public final String value;

    EligibilityStatus(String value) {
        this.value = value;
    }
}
