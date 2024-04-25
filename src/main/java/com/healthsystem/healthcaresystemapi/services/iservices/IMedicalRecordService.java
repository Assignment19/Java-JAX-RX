package com.healthsystem.healthcaresystemapi.services.iservices;

import com.healthsystem.healthcaresystemapi.models.MedicalRecord;
import com.healthsystem.healthcaresystemapi.models.MedicalRecordWithPatient;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import java.util.List;

public interface IMedicalRecordService {
    StandardResponse<MedicalRecordWithPatient> getMedicalRecordById(int id);

    StandardResponse<MedicalRecordWithPatient> saveMedicalRecord(MedicalRecord medicalRecord);

    StandardResponse<MedicalRecord> removeMedicalRecord(int id);

    StandardResponse<MedicalRecord> updateMedicalRecord(int id, MedicalRecord medicalRecord);

    StandardResponse<List<MedicalRecordWithPatient>> getAllMedicalRecord();
}
