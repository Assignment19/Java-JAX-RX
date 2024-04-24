
package com.healthsystem.healthcaresystemapi.dao.idao;
import com.healthsystem.healthcaresystemapi.models.Patient;
import java.util.List;

public interface IPatientDAO {
  public Patient findById(int id);
  public void createPatient(Patient patient);
  public void updatePatient(Patient patient);
  public void deletePatient(int id);
  public List<Patient> getAllPatients();
  public int getNextAvailableId();
}
