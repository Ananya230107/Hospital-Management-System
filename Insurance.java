
public class Insurance {

    private String provider;
    private String policyNumber;
    private String coveredSpecialization;

    public Insurance(String provider, String policyNumber, String coveredSpecialization) {
        this.provider = provider;
        this.policyNumber = policyNumber;
        this.coveredSpecialization = coveredSpecialization;
    }

    public String getProvider() {
        return provider;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getCoveredSpecialization() {
        return coveredSpecialization;
    }

    public boolean covers(String specialization) {
        return coveredSpecialization.equalsIgnoreCase("All")
                || coveredSpecialization.equalsIgnoreCase(specialization);
    }
}
