package com.healthsystem.healthcaresystemapi.dao.idao;

import com.healthsystem.healthcaresystemapi.models.Appointment;

import java.util.List;

public interface IAppoinmentDAO {
    Appointment findById(int id);

    void createAppoinment(Appointment appointment);

    int getNextAvailableId();

    void updateAppoinment(Appointment appointment);

    void deleteAppoinment(int id);

    List<Appointment> getAllAppoinment();
}
