package com.healthsystem.healthcaresystemapi.dao;

import com.healthsystem.healthcaresystemapi.dao.idao.IPrescriptionDAO;
import com.healthsystem.healthcaresystemapi.models.Prescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrescriptionDAO implements IPrescriptionDAO {
    private static final Map<Integer, Prescription> prescriptionMap = new HashMap<>();

    @Override
    public Prescription findById(int id) {
        return prescriptionMap.get(id);
    }

    @Override
    public void createPrescription(Prescription prescription) {
        // Generate a unique ID for the patient
        int nextId = getNextAvailableId();
        prescription.setId(nextId);
        prescriptionMap.put(nextId, prescription);
    }

    @Override
    public int getNextAvailableId() {
        // Find the maximum ID currently in use and increment by 1
        int maxId = prescriptionMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxId + 1;
    }

    @Override
    public void updatePrescription(Prescription prescription) {
        prescriptionMap.put((int)prescription.getId(), prescription);
    }

    @Override
    public void deletePrescription(int id) {
        prescriptionMap.remove(id);
    }

    @Override
    public List<Prescription> getAllPrescription() {
        List<Prescription> allPrescription = new ArrayList<>();
        for (Map.Entry<Integer, Prescription> entry : prescriptionMap.entrySet()) {
            allPrescription.add(entry.getValue());
        }
        return allPrescription;
    }

}
