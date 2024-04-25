package com.healthsystem.healthcaresystemapi.dao.idao;

import com.healthsystem.healthcaresystemapi.models.Doctor;

import java.util.List;

public interface IDoctorDAO {
    Doctor findById(int id);

    void createDoctor(Doctor doctor);

    int getNextAvailableId();

    void updateDoctor(Doctor doctor);

    void deleteDoctor(int id);

    List<Doctor> getAllDoctor();
}
