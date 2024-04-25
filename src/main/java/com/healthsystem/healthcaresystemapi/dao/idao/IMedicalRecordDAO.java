package com.healthsystem.healthcaresystemapi.dao.idao;

import com.healthsystem.healthcaresystemapi.models.MedicalRecord;

import java.util.List;

public interface IMedicalRecordDAO {
    MedicalRecord findById(int id);

    void createMedicalRecord(MedicalRecord medicalRecord);

    int getNextAvailableId();

    void updateMedicalRecord(MedicalRecord medicalRecord);

    void deleteMedicalRecord(int id);

    List<MedicalRecord> getAllMedicalRecord();
}
