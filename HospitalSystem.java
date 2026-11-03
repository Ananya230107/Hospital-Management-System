
import java.util.ArrayList;
import java.util.Scanner;

public class HospitalSystem {

    public static void main(String[] args) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Patient> patients = new ArrayList<>();

        // Initializing the 7 Doctors
        doctors.add(new Doctor(101, "Smith", 45, "Male", "Cardiology", "Mon - Fri (9 AM - 2 PM)"));
        doctors.add(new Doctor(102, "Adams", 38, "Female", "Pediatrics", "Tue - Sat (11 AM - 5 PM)"));
        doctors.add(new Doctor(103, "Mehta", 50, "Male", "Neurology", "Mon - Thu (10 AM - 4 PM)"));
        doctors.add(new Doctor(104, "Kapoor", 42, "Female", "Dermatology", "Wed - Sun (12 PM - 6 PM)"));
        doctors.add(new Doctor(105, "Verma", 36, "Male", "Orthopedics", "Mon - Fri (8 AM - 1 PM)"));
        doctors.add(new Doctor(106, "Sharma", 48, "Female", "Oncology", "Mon - Wed (9 AM - 3 PM)"));
        doctors.add(new Doctor(107, "Patel", 41, "Male", "General Medicine", "Daily (10 AM - 6 PM)"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("==========================================");
        System.out.println("   HOSPITAL MANAGEMENT SYSTEM (BACKEND)   ");
        System.out.println("==========================================");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. View All Doctors");
            System.out.println("2. Register New Patient");
            System.out.println("3. View All Registered Patients");
            System.out.println("4. Check Insurance Coverage & Doctor Availability");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n=== REGISTERED DOCTORS DIRECTORY ===");
                    for (Doctor doc : doctors) {
                        doc.displayDetails();
                    }
                    break;

                case 2:
                    System.out.println("\n=== REGISTER NEW PATIENT ===");
                    System.out.print("Enter Patient ID: ");
                    int pId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Full Name: ");
                    String pName = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int pAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Gender: ");
                    String pGender = scanner.nextLine();
                    System.out.print("Enter Ailment / Diagnosis: ");
                    String pAilment = scanner.nextLine();

                    System.out.print("Does the patient have insurance? (y/n): ");
                    String hasIns = scanner.nextLine();

                    Insurance insurance = null;
                    if (hasIns.equalsIgnoreCase("y")) {
                        System.out.print("Enter Insurance Provider Name (e.g., BlueCross): ");
                        String provider = scanner.nextLine();
                        System.out.print("Enter Policy Number: ");
                        String policyNo = scanner.nextLine();
                        System.out.print("Enter Covered Specialization (e.g., Cardiology or 'All'): ");
                        String spec = scanner.nextLine();
                        insurance = new Insurance(provider, policyNo, spec);
                    }

                    patients.add(new Patient(pId, pName, pAge, pGender, pAilment, insurance));
                    System.out.println("✅ Patient record saved successfully in backend!");
                    break;

                case 3:
                    System.out.println("\n=== REGISTERED PATIENTS DIRECTORY ===");
                    if (patients.isEmpty()) {
                        System.out.println("No patients registered in the system yet.");
                    } else {
                        for (Patient pat : patients) {
                            pat.displayDetails();
                            System.out.println("--------------------------------------------------");
                        }
                    }
                    break;

                case 4:
                    System.out.println("\n=== INSURANCE COVERAGE & APPOINTMENT CHECKER ===");
                    System.out.print("Enter Patient ID: ");
                    int checkPatId = scanner.nextInt();
                    System.out.print("Enter Doctor ID (101 - 107): ");
                    int checkDocId = scanner.nextInt();

                    Patient foundPatient = null;
                    for (Patient p : patients) {
                        if (p.getId() == checkPatId) {
                            foundPatient = p;
                            break;
                        }
                    }

                    Doctor foundDoctor = null;
                    for (Doctor d : doctors) {
                        if (d.getId() == checkDocId) {
                            foundDoctor = d;
                            break;
                        }
                    }

                    if (foundPatient == null || foundDoctor == null) {
                        System.out.println("❌ Error: Patient ID or Doctor ID not found in system.");
                    } else {
                        System.out.println("\n[Verification Report]");
                        System.out.println("Patient: " + foundPatient.getName() + " | Doctor: Dr. " + foundDoctor.getName() + " (" + foundDoctor.getSpecialization() + ")");

                        if (!foundPatient.hasInsurance()) {
                            System.out.println("⚠️ Status: NOT COVERED (Self-Pay)");
                            System.out.println("🕒 Doctor Schedule: " + foundDoctor.getName() + " is available " + foundDoctor.getAvailability());
                        } else if (foundPatient.getInsurance().covers(foundDoctor.getSpecialization())) {
                            System.out.println("✅ Status: ELIGIBLE / COVERED!");
                            System.out.println("Provider: " + foundPatient.getInsurance().getProvider() + " covers " + foundDoctor.getSpecialization());
                            System.out.println("🕒 Doctor Schedule: " + foundDoctor.getName() + " is available " + foundDoctor.getAvailability());
                        } else {
                            System.out.println("⚠️ Status: OUT-OF-NETWORK / NOT COVERED");
                            System.out.println("Reason: Insurance covers '" + foundPatient.getInsurance().getCoveredSpecialization()
                                    + "', but doctor specializes in '" + foundDoctor.getSpecialization() + "'.");
                            System.out.println("🕒 Doctor Schedule: " + foundDoctor.getName() + " is available " + foundDoctor.getAvailability());
                        }
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting Hospital Management System. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Please choose an option between 1 and 5.");
            }
        }
        scanner.close();
    }
}
