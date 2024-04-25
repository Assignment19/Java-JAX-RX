package com.healthsystem.healthcaresystemapi.services.iservices;

import com.healthsystem.healthcaresystemapi.models.Doctor;
import com.healthsystem.healthcaresystemapi.models.DoctorWithPerson;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import java.util.List;

public interface IDoctorService {
    StandardResponse<DoctorWithPerson> getDoctorById(int id);

    StandardResponse<DoctorWithPerson> saveDoctor(Doctor doctor);

    StandardResponse<Doctor> removeDoctor(int id);

    StandardResponse<List<DoctorWithPerson>> getAllDoctor();

    StandardResponse<Doctor> updateDoctor(int id, Doctor doctor);
}
