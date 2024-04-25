package com.healthsystem.healthcaresystemapi.services;

import com.healthsystem.healthcaresystemapi.dao.DoctorDAO;
import com.healthsystem.healthcaresystemapi.dao.PersonDAO;
import com.healthsystem.healthcaresystemapi.models.Doctor;
import com.healthsystem.healthcaresystemapi.models.DoctorWithPerson;
import com.healthsystem.healthcaresystemapi.services.iservices.IDoctorService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import lombok.var;

import java.util.ArrayList;
import java.util.List;

public class DoctorService implements IDoctorService {

    private final DoctorDAO doctorDAO;
    private final PersonDAO personDAO;

    public DoctorService(){
        this.doctorDAO = new DoctorDAO();
        this.personDAO = new PersonDAO();
    }

    @Override
    public StandardResponse<DoctorWithPerson> getDoctorById(int id){
        try{
            var doctor = doctorDAO.findById(id);
            if(doctor!=null){
                var personId = doctor.getPersonId();
                var person = personDAO.findById((int)personId);
                if(person!=null){

                    DoctorWithPerson doctorWithPerson = new DoctorWithPerson();
                    doctorWithPerson.setPerson(person);
                    doctorWithPerson.setId(doctor.getId());
                    doctorWithPerson.setTitle(doctor.getTitle());


                return new StandardResponse<>(200, "Success", doctorWithPerson, true);}

                else{
                    return new StandardResponse<>(400, "Person not available", null, true);

                }

            }
            else{
                return new StandardResponse<>(400, "doctor not available", null, false);

            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<DoctorWithPerson> saveDoctor(Doctor doctor){
        try{
            var personId = doctor.getPersonId();
            var person = personDAO.findById((int)personId);
            if(person!=null){
            var result = doctorDAO.findById((int)doctor.getId());
            if(result==null){
                doctorDAO.createDoctor(doctor);
                DoctorWithPerson doctorWithPerson = new DoctorWithPerson();
                doctorWithPerson.setPerson(person);
                doctorWithPerson.setTitle(doctor.getTitle());
                doctorWithPerson.setId(doctor.getId());
                return new StandardResponse<>(200, "Create Success", doctorWithPerson, true);

            }
            else{
                return new StandardResponse<>(400, "Doctor Already Inserted", null, false);

            }}
            else{
                return new StandardResponse<>(400, "Person not available", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<Doctor> removeDoctor(int id){
        try{
            var result = doctorDAO.findById(id);
            if(result!=null){
                doctorDAO.deleteDoctor(id);
                return new StandardResponse<>(200, "Remove Success", result, true);
            }

            else{
                return new StandardResponse<>(400, "Doctor not found", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }

    }

    @Override
    public StandardResponse<List<DoctorWithPerson>> getAllDoctor(){
        try{
            List<DoctorWithPerson> userList = new ArrayList<>();
            List<Doctor> doctorList = doctorDAO.getAllDoctor();
            for(int i = 0; i<doctorList.size(); i++){
                DoctorWithPerson doctorWithPerson = new DoctorWithPerson();
                var doctor = doctorList.get(i);
                var personId = doctor.getPersonId();
                var person = personDAO.findById((int)personId);
                doctorWithPerson.setId(doctor.getId());
                doctorWithPerson.setPerson(person);
                doctorWithPerson.setTitle(doctor.getTitle());
                userList.add(i, doctorWithPerson);
            }
            return new StandardResponse<>(200, "Doctor list retrieve successfully", userList, true);
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }
    }

    @Override
    public StandardResponse<Doctor> updateDoctor(int id, Doctor doctor){
        try{
            var result = doctorDAO.findById(id);
            if(result!=null){
                doctorDAO.updateDoctor(doctor);
                return new StandardResponse<>(200, "Doctor update successfull", doctor, true);
            }
            else{
                return new StandardResponse<>(400, "Doctor not available", doctor, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), doctor, false);
        }
    }
}
