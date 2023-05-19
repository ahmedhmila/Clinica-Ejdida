import java.util.List;
public interface IPatient {
    void addPatient(Patient patient);
    void evacuatePatient(String nCIN);
    Patient getPatientByNCIN(String nCIN);
    List<Patient> getAllPatients();
    void updatePatient(String nCIN, Patient patient);

}
