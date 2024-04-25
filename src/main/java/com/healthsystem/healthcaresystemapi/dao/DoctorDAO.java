package com.healthsystem.healthcaresystemapi.dao;

import com.healthsystem.healthcaresystemapi.dao.idao.IDoctorDAO;
import com.healthsystem.healthcaresystemapi.models.Doctor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorDAO implements IDoctorDAO {

    private static final Map<Integer, Doctor> doctorMap = new HashMap<>();

    @Override
    public Doctor findById(int id) {
        return doctorMap.get(id);
    }

    @Override
    public void createDoctor(Doctor doctor) {
        // Generate a unique ID for the patient
        int nextId = getNextAvailableId();
        doctor.setId(nextId);
        doctorMap.put(nextId, doctor);
    }

    @Override
    public int getNextAvailableId() {
        // Find the maximum ID currently in use and increment by 1
        int maxId = doctorMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxId + 1;
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        doctorMap.put((int)doctor.getId(), doctor);
    }

    @Override
    public void deleteDoctor(int id) {
        doctorMap.remove(id);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        List<Doctor> allDoctors = new ArrayList<>();
        for (Map.Entry<Integer, Doctor> entry : doctorMap.entrySet()) {
            allDoctors.add(entry.getValue());
        }
        return allDoctors;
    }
}
