package com.healthsystem.healthcaresystemapi.dao;

import com.healthsystem.healthcaresystemapi.dao.idao.IMedicalRecordDAO;
import com.healthsystem.healthcaresystemapi.models.MedicalRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalRecordDAO implements IMedicalRecordDAO {
    private static final Map<Integer, MedicalRecord> medicalrecordMap = new HashMap<>();

    @Override
    public MedicalRecord findById(int id) {
        return medicalrecordMap.get(id);
    }

    @Override
    public void createMedicalRecord(MedicalRecord medicalRecord) {
        // Generate a unique ID for the patient
        int nextId = getNextAvailableId();
        medicalRecord.setId(nextId);
        medicalrecordMap.put(nextId, medicalRecord);
    }

    @Override
    public int getNextAvailableId() {
        // Find the maximum ID currently in use and increment by 1
        int maxId = medicalrecordMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxId + 1;
    }

    @Override
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        medicalrecordMap.put((int)medicalRecord.getId(), medicalRecord);
    }

    @Override
    public void deleteMedicalRecord(int id) {
        medicalrecordMap.remove(id);
    }

    @Override
    public List<MedicalRecord> getAllMedicalRecord() {
        List<MedicalRecord> allMedicalRecord = new ArrayList<>();
        for (Map.Entry<Integer, MedicalRecord> entry : medicalrecordMap.entrySet()) {
            allMedicalRecord.add(entry.getValue());
        }
        return allMedicalRecord;
    }

}
