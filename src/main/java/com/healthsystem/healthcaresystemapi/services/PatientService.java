package com.healthsystem.healthcaresystemapi.services;
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
    
    public PatientService(){
    this.patientDAO = new PatientDAO();
    }
    
    @Override
    public StandardResponse<Patient> getPatientById(int id){
    try{
    if(patientDAO.findById(id)!=null){
    var patient = patientDAO.findById(id);
    return new StandardResponse<>(200, "Success", patient, true);

    }
    else{
        return new StandardResponse<>(400, "Patient not available", null, false);

    }
    }
    catch(Exception ex){
    return new StandardResponse<>(400, ex.getMessage(), null, false);}
    }
    
    
    @Override
    public StandardResponse<Patient> savePatient(Patient patient){
        try{
        var result = patientDAO.findById((int)patient.getId());
        if(result==null){
        patientDAO.createPatient(patient);
        return new StandardResponse<>(200, "Create Success", patient, true);

        
        }
        else{
         return new StandardResponse<>(400, "User Already Inserted", patient, false);

        }
        }
        catch(Exception ex){
        return new StandardResponse<>(400, ex.getMessage(), null, false);}
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
    return new StandardResponse<>(400, "User not found", result, false);

    }
    }
    catch(Exception ex){
        return new StandardResponse<>(400, ex.getMessage(), null, false);
    }
    
    }
    
    
    @Override
    public StandardResponse<List<Patient>> getAllPatients(){
    try{
        List<Patient> patientList = patientDAO.getAllPatients();
        return new StandardResponse<>(200, "Patient list retrieve successfull", patientList, true);
    }
    catch(Exception ex){
    return new StandardResponse<>(400, ex.getMessage(), null, false);
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
        return new StandardResponse<>(400, "Patient not available", patient, false);
        }
    }
        
    catch(Exception ex){
        return new StandardResponse<>(400, ex.getMessage(), patient, false);
}
}
    
}
