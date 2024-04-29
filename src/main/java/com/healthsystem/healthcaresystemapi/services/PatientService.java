package com.healthsystem.healthcaresystemapi.services;
import com.healthsystem.healthcaresystemapi.dao.PersonDAO;
import com.healthsystem.healthcaresystemapi.models.Doctor;
import com.healthsystem.healthcaresystemapi.models.DoctorWithPerson;
import com.healthsystem.healthcaresystemapi.models.PatientWithPerson;
import com.healthsystem.healthcaresystemapi.services.iservices.IPatientService;
import com.healthsystem.healthcaresystemapi.dao.PatientDAO;
import com.healthsystem.healthcaresystemapi.models.Patient;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import lombok.var;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientService implements IPatientService{
    private final PatientDAO patientDAO;
    private final PersonDAO personDAO;
    
    public PatientService(){
    this.patientDAO = new PatientDAO();
    this.personDAO = new PersonDAO();
    }
    
    @Override
    public StandardResponse<PatientWithPerson> getPatientById(int id){
        try{
            var patient = patientDAO.findById(id);
            if(patient!=null){
                var personId = patient.getPersonId();
                var person = personDAO.findById((int)personId);
                if(person!=null){
                    PatientWithPerson patientWithPerson = new PatientWithPerson();
                    patientWithPerson.setPerson(person);
                    patientWithPerson.setId(patient.getId());
                    patientWithPerson.setMedicalHistory(patient.getMedicalHistory());
                    patientWithPerson.setCurrentHealthStatus(patient.getCurrentHealthStatus());
                    return new StandardResponse<>(200, "Success", patientWithPerson, true);}
                else{
                    return new StandardResponse<>(404, "Person not available", null, true);
                }
            }
            else{
                return new StandardResponse<>(404, "Patient not available", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);}
    }
    
    
    @Override
    public StandardResponse<PatientWithPerson> savePatient(Patient patient){
        try{
            var personId = patient.getPersonId();
            var person = personDAO.findById((int)personId);
            if(person!=null){
                var result = patientDAO.findById((int)patient.getId());
                if(result==null){
                    patientDAO.createPatient(patient);
                    PatientWithPerson patientWithPerson = new PatientWithPerson();
                    patientWithPerson.setPerson(person);
                    patientWithPerson.setMedicalHistory(patient.getMedicalHistory());
                    patientWithPerson.setId(patient.getId());
                    patientWithPerson.setCurrentHealthStatus(patient.getCurrentHealthStatus());
                    return new StandardResponse<>(200, "Create Success", patientWithPerson, true);
                }
                else{
                    return new StandardResponse<>(404, "Patient Already Inserted", null, false);
                }}
            else{
                return new StandardResponse<>(404, "Person not available", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);}
    } 
    
    @Override
    public StandardResponse<Patient> removePatient(int id){
        try{
            var result = patientDAO.findById(id);
            if(result!=null){
                patientDAO.deletePatient(id);
                return new StandardResponse<>(200, "Remove Success", result, true);
            }
            else{
                return new StandardResponse<>(404, "Patient not found to remove", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);
        }
    }
    
    
    @Override
    public StandardResponse<List<PatientWithPerson>> getAllPatients(){
        try{
            List<PatientWithPerson> userList = new ArrayList<>();
            List<Patient> patientList = patientDAO.getAllPatients();
            for(int i = 0; i<patientList.size(); i++){
                PatientWithPerson patientWithPerson = new PatientWithPerson();
                var patient = patientList.get(i);
                var personId = patient.getPersonId();
                var person = personDAO.findById((int)personId);
                patientWithPerson.setId(patient.getId());
                patientWithPerson.setPerson(person);
                patientWithPerson.setCurrentHealthStatus(patient.getCurrentHealthStatus());
                patientWithPerson.setMedicalHistory(patient.getMedicalHistory());
                userList.add(i, patientWithPerson);
            }
            return new StandardResponse<>(200, "Doctor list retrieve successfull", userList, true);
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);
        }
    }
    
    @Override
    public StandardResponse<Patient> updatePatient(int id, Patient patient){
        try{
            var result = patientDAO.findById(id);
            if(result!=null){
                patientDAO.updatePatient(patient);
                return new StandardResponse<>(200, "Patient update successfull", patient, true);
            }
            else{
                return new StandardResponse<>(404, "Patient not available to update", patient, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), patient, false);
        }
    }
}
