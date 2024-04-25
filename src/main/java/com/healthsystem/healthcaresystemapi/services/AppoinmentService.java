package com.healthsystem.healthcaresystemapi.services;

import com.healthsystem.healthcaresystemapi.dao.AppoinmentDAO;
import com.healthsystem.healthcaresystemapi.dao.DoctorDAO;
import com.healthsystem.healthcaresystemapi.dao.PatientDAO;
import com.healthsystem.healthcaresystemapi.dao.PersonDAO;
import com.healthsystem.healthcaresystemapi.models.*;
import com.healthsystem.healthcaresystemapi.services.iservices.IAppoinmentService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import lombok.var;

import java.util.ArrayList;
import java.util.List;

public class AppoinmentService implements IAppoinmentService {



    private final DoctorDAO doctorDAO;
    private final PersonDAO personDAO;
    private final PatientDAO patientDAO;
    private final AppoinmentDAO appoinmentDAO;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppoinmentService(){
        this.doctorDAO = new DoctorDAO();
        this.personDAO = new PersonDAO();
        this.appoinmentDAO = new AppoinmentDAO();
        this.doctorService = new DoctorService();
        this.patientService = new PatientService();
        this.patientDAO = new PatientDAO();
    }


    @Override
    public StandardResponse<AppoinmentWithDoctorAndPatient> getAppoinmentById(int id){
        try{
            var appoinment = appoinmentDAO.findById(id);
            if(appoinment!=null){
                var doctorId = appoinment.getDoctorID();
                var patientId = appoinment.getPatientId();
                var patient = patientService.getPatientById((int)patientId).data;
                var doctor = doctorService.getDoctorById((int)doctorId).data;
                if(patient!=null&&doctor!=null){
                    AppoinmentWithDoctorAndPatient appoinmentWithDoctorAndPatient = new AppoinmentWithDoctorAndPatient(appoinment.getAppoinmentDate(),appoinment.getDescription(),
                         patient, doctor );
                    return new StandardResponse<>(200, "Appointment retrieve Success", appoinmentWithDoctorAndPatient, true);}

                else{
                    return new StandardResponse<>(400, "Person or Doctor not available", null, true);

                }

            }
            else{
                return new StandardResponse<>(400, "Appointment not available", null, false);

            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<AppoinmentWithDoctorAndPatient> saveAppoinment(Appointment appointment){
        try{
            var patientId = appointment.getPatientId();
            var doctorId = appointment.getDoctorID();
            var patient = patientService.getPatientById((int)patientId).getData();
            var doctor = doctorService.getDoctorById((int)doctorId).getData();
            if(patient!=null&&doctor!=null){
                var result = appoinmentDAO.findById((int)appointment.getId());
                if(result==null){
                    appoinmentDAO.createAppoinment(appointment);
                    AppoinmentWithDoctorAndPatient appoinmentWithDoctorAndPatient = new AppoinmentWithDoctorAndPatient(appointment.getAppoinmentDate(),appointment.getDescription(),
                            patient, doctor );

                    return new StandardResponse<>(200, "Appointment Create Success", appoinmentWithDoctorAndPatient, true);

                }
                else{
                    return new StandardResponse<>(400, "Appointment Already Inserted", null, false);

                }}
            else{
                return new StandardResponse<>(400, "Person and Doctor not available", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<Appointment> removeAppoinment(int id){
        try{
            var result = appoinmentDAO.findById(id);
            if(result!=null){
                appoinmentDAO.deleteAppoinment(id);
                return new StandardResponse<>(200, "Appointment Remove Success", result, true);
            }

            else{
                return new StandardResponse<>(400, "Appointment not found", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }

    }

    @Override
    public StandardResponse<Appointment> updateAppoinment(int id, Appointment appointment){
        try{
            var result = appoinmentDAO.findById(id);
            if(result!=null){
                appoinmentDAO.updateAppoinment(appointment);
                return new StandardResponse<>(200, "Appointment update successfull", appointment, true);
            }
            else{
                return new StandardResponse<>(400, "Appointment not available", appointment, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }
    }

    @Override
    public StandardResponse<List<AppoinmentWithDoctorAndPatient>> getAllAppoinment(){
        try{
            List<AppoinmentWithDoctorAndPatient> userList = new ArrayList<>();
            List<Appointment> appoinmentList = appoinmentDAO.getAllAppoinment();
            for(int i = 0; i<appoinmentList.size(); i++){
                var doctorId = appoinmentList.get(i).getDoctorID();
                var patientId = appoinmentList.get(i).getPatientId();
                var doctor = doctorService.getDoctorById((int)doctorId).getData();
                var patient = patientService.getPatientById((int)patientId).getData();
                AppoinmentWithDoctorAndPatient appoinmentWithDoctorAndPatient = new AppoinmentWithDoctorAndPatient(appoinmentList.get(i).getAppoinmentDate(), appoinmentList.get(i).getDescription(),
                        patient, doctor );
                userList.add(i, appoinmentWithDoctorAndPatient);
            }
            return new StandardResponse<>(200, "Appointment list retrieve successfully", userList, true);
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }
    }


}
