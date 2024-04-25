package com.healthsystem.healthcaresystemapi.services.iservices;
import com.healthsystem.healthcaresystemapi.models.PatientWithPerson;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import com.healthsystem.healthcaresystemapi.models.Patient;
import java.util.List;

public interface IPatientService {
    public StandardResponse<PatientWithPerson> getPatientById(int id);
    public StandardResponse<PatientWithPerson> savePatient(Patient patient);
    public StandardResponse<Patient> removePatient(int id);
    public StandardResponse<List<PatientWithPerson>> getAllPatients();
    public StandardResponse<Patient> updatePatient(int id, Patient patient);
}
