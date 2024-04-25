package com.healthsystem.healthcaresystemapi.services.iservices;

import com.healthsystem.healthcaresystemapi.models.AppoinmentWithDoctorAndPatient;
import com.healthsystem.healthcaresystemapi.models.Appointment;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import java.util.List;

public interface IAppoinmentService {
    StandardResponse<AppoinmentWithDoctorAndPatient> getAppoinmentById(int id);

    StandardResponse<AppoinmentWithDoctorAndPatient> saveAppoinment(Appointment appointment);

    StandardResponse<Appointment> removeAppoinment(int id);

    StandardResponse<Appointment> updateAppoinment(int id, Appointment appointment);

    StandardResponse<List<AppoinmentWithDoctorAndPatient>> getAllAppoinment();
}
