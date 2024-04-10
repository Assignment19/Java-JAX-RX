package com.healthsystem.healthcaresystemapi.services.iservices;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import com.healthsystem.healthcaresystemapi.models.Patient;

public interface IPatientService {
    public StandardResponse<Patient> getPatientById(int id);
    public StandardResponse<Patient> savePatient(Patient patient);
}
