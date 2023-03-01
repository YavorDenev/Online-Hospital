package DigRz.OnlineHospital.constants;

public enum Role {

    ROLE_PATIENT("Patient"),
    ROLE_DOCTOR("Doctor"),
    ROLE_ADMIN("Admin");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
