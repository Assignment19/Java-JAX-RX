package com.healthsystem.healthcaresystemapi.services.iservices;

import com.healthsystem.healthcaresystemapi.models.Prescription;
import com.healthsystem.healthcaresystemapi.models.PrescriptionWithPatientAndDoctor;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import java.util.List;

public interface IPrescriptionService {
    StandardResponse<PrescriptionWithPatientAndDoctor> getPrescriptionById(int id);

    StandardResponse<PrescriptionWithPatientAndDoctor> savePrescription(Prescription prescription);

    StandardResponse<Prescription> removePrescription(int id);

    StandardResponse<Prescription> updatePrescription(int id, Prescription prescription);

    StandardResponse<List<PrescriptionWithPatientAndDoctor>> getAllPrescriptions();
}
