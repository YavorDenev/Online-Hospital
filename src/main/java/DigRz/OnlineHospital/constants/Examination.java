package DigRz.OnlineHospital.constants;

public enum Examination {

    INITIAL("initial"),
    SECONDARY("secondary"),
    CONSULTATION("consultation"),
    PROCEDURE("procedure");

    private final String value;

    private Examination(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
