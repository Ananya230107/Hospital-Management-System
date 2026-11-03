
public class Doctor extends Person {

    private String specialization;
    private String availability;

    public Doctor(int id, String name, int age, String gender, String specialization, String availability) {
        super(id, name, age, gender);
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getAvailability() {
        return availability;
    }

    @Override
    public void displayDetails() {
        System.out.println("ID: " + getId()
                + " | Dr. " + getName()
                + " | Age: " + getAge()
                + " | Gender: " + getGender()
                + " | Specialization: " + specialization
                + " | Hours: " + availability);
    }
}
