
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientImpl implements IPatient{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    // ...

    public void addPatient(Patient patient) {
       
    
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM patients WHERE nCIN = ?")) {
    
            stmt.setString(1, patient.getNCIN());
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        System.out.println("Error: Patient with nCIN " + patient.getNCIN() + " already exists in the database.");
                        return; 
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return; 
        }
    
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO patients (nom, sexe, nCIN, maladie, chamber, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)")) {
    
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getSexe());
            stmt.setString(3, patient.getNCIN());
            stmt.setString(4, patient.getMaladie());
            stmt.setInt(5, patient.getChamber());
            stmt.setString(6, patient.getPhoneNumber());
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public Patient getPatientByNCIN(String nCIN) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patients WHERE nCIN = ?")) {
            stmt.setString(1, nCIN);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    String sexe = rs.getString("sexe");
                    String maladie = rs.getString("maladie");
                    int chamber = rs.getInt("chamber");
                    String phoneNumber = rs.getString("phoneNumber");

                    return new Patient(nom, sexe, nCIN, maladie, chamber, phoneNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patients");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nom = rs.getString("nom");
                String sexe = rs.getString("sexe");
                String nCIN = rs.getString("nCIN");
                String maladie = rs.getString("maladie");
                int chamber = rs.getInt("chamber");
                String phoneNumber = rs.getString("phoneNumber");

                patients.add(new Patient(nom, sexe, nCIN, maladie, chamber, phoneNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public void updatePatient(String nCIN, Patient patient) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE patients SET nom = ?, sexe = ?, maladie = ?, chamber = ?, phoneNumber = ? WHERE nCIN = ?")) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getSexe());
            stmt.setString(3, patient.getMaladie());
            stmt.setInt(4, patient.getChamber());
            stmt.setString(5, patient.getPhoneNumber());
            stmt.setString(6, nCIN);
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    

    @Override
    
    public void evacuatePatient(String nCIN) {
        Patient patient = getPatientByNCIN(nCIN);
        
        if (patient != null) {
            // Update the patient's record in the database
            patient.setChamber("0");
            updatePatient(nCIN,patient);
            System.out.println("Patient with nCIN " + nCIN + " has been evacuated.");
        } else {
            System.out.println("Patient with nCIN " + nCIN + " does not exist in the database.");
        }
    }
    
    

}
