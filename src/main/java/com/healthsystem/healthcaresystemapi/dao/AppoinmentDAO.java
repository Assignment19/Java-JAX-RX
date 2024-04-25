package com.healthsystem.healthcaresystemapi.dao;

import com.healthsystem.healthcaresystemapi.dao.idao.IAppoinmentDAO;
import com.healthsystem.healthcaresystemapi.models.Appointment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppoinmentDAO implements IAppoinmentDAO {
    private static final Map<Integer, Appointment> appoinmentMap = new HashMap<>();

    @Override
    public Appointment findById(int id) {
        return appoinmentMap.get(id);
    }

    @Override
    public void createAppoinment(Appointment appointment) {
        // Generate a unique ID for the patient
        int nextId = getNextAvailableId();
        appointment.setId(nextId);
        appoinmentMap.put(nextId, appointment);
    }

    @Override
    public int getNextAvailableId() {
        // Find the maximum ID currently in use and increment by 1
        int maxId = appoinmentMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxId + 1;
    }

    @Override
    public void updateAppoinment(Appointment appointment) {
        appoinmentMap.put((int)appointment.getId(), appointment);
    }

    @Override
    public void deleteAppoinment(int id) {
        appoinmentMap.remove(id);
    }

    @Override
    public List<Appointment> getAllAppoinment() {
        List<Appointment> allAppoinments = new ArrayList<>();
        for (Map.Entry<Integer, Appointment> entry : appoinmentMap.entrySet()) {
            allAppoinments.add(entry.getValue());
        }
        return allAppoinments;
    }

}
