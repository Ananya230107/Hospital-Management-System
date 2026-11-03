
public class Patient extends Person {

    private String ailment;
    private Insurance insurance; // Optional / Null if Self-Pay

    public Patient(int id, String name, int age, String gender, String ailment, Insurance insurance) {
        super(id, name, age, gender);
        this.ailment = ailment;
        this.insurance = insurance;
    }

    public String getAilment() {
        return ailment;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public boolean hasInsurance() {
        return insurance != null;
    }

    @Override
    public void displayDetails() {
        System.out.println("Patient ID: " + getId()
                + " | Name: " + getName()
                + " | Age: " + getAge()
                + " | Gender: " + getGender()
                + " | Ailment: " + ailment);
        if (hasInsurance()) {
            System.out.println("   -> Insurance: " + insurance.getProvider()
                    + " (Policy: " + insurance.getPolicyNumber()
                    + ", Covers: " + insurance.getCoveredSpecialization() + ")");
        } else {
            System.out.println("   -> Payment Status: Self-Pay (No Insurance Policy)");
        }
    }
}
