package com.healthsystem.healthcaresystemapi.dao;
import com.healthsystem.healthcaresystemapi.dao.idao.IPatientDAO;
import com.healthsystem.healthcaresystemapi.models.Patient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientDAO implements IPatientDAO{
     private static final Map<Integer, Patient> patientMap = new HashMap<>();
     
    @Override
    public Patient findById(int id) {
        return patientMap.get(id);
    }

    @Override
    public void createPatient(Patient patient) {
        // Generate a unique ID for the patient
        int nextId = getNextAvailableId();
        patient.setId(nextId);
        // Add the patient to the map with the new ID
        patientMap.put(nextId, patient);
    }

    @Override
    public int getNextAvailableId() {
        // Find the maximum ID currently in use and increment by 1
        int maxId = patientMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxId + 1;
    }

    @Override
    public void updatePatient(Patient patient) {
        patientMap.put((int)patient.getId(), patient);
    }

    @Override
    public void deletePatient(int id) {
        patientMap.remove(id);
    }
    
    @Override
    public List<Patient> getAllPatients() {
    List<Patient> allPatients = new ArrayList<>();
    for (Map.Entry<Integer, Patient> entry : patientMap.entrySet()) {
    allPatients.add(entry.getValue());
    }
    return allPatients;
    }   
}
