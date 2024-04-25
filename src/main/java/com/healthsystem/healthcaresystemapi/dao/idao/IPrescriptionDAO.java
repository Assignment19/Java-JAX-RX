package com.healthsystem.healthcaresystemapi.dao.idao;

import com.healthsystem.healthcaresystemapi.models.Prescription;

import java.util.List;

public interface IPrescriptionDAO {
    Prescription findById(int id);

    void createPrescription(Prescription prescription);

    int getNextAvailableId();

    void updatePrescription(Prescription prescription);

    void deletePrescription(int id);

    List<Prescription> getAllPrescription();
}
