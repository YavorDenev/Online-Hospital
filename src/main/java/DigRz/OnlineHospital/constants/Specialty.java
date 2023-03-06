package DigRz.OnlineHospital.constants;

public enum Specialty {

    ANESTHESIOLOGY("Anesthesiology"),
    CARDIOLOGY("Cardiology"),
    DERMATOLOGY("Dermatology"),
    ENDOCRINOLOGY("Endocrinology"),
    GASTROENTEROLOGY("Gastroenterology"),
    GYNECOLOGY("Gynecology"),
    INTERNAL_DISEASES("Internal_diseases"),
    NEPHROLOGY("Nephrology"),
   NEUROLOGY("Neurology"),
    OPHTHALMOLOGY("Ophthalmology"),
    ORTHOPEDICS("Orthopedics"),
    PSYCHIATRY("Psychiatry"),
    RHEUMATOLOGY("Rheumatology"),
    SURGERY("Surgery"),
    UROLOGY("Urology");

    private final String value;

    private Specialty(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
