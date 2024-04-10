package com.healthsystem.healthcaresystemapi.services;
import com.healthsystem.healthcaresystemapi.services.iservices.IPatientService;
import com.healthsystem.healthcaresystemapi.dao.PatientDAO;
import com.healthsystem.healthcaresystemapi.models.Patient;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

public class PatientService implements IPatientService{
    private final PatientDAO patientDAO;
    
    public PatientService(){
    this.patientDAO = new PatientDAO();
    }
    
    @Override
    public StandardResponse<Patient> getPatientById(int id){
    if(patientDAO.findById(id)!=null){
    var patient = patientDAO.findById(id);
    StandardResponse response = new StandardResponse(200, "Success", patient, true);
    return response;}
    else{
        StandardResponse response = new StandardResponse(400, "faild", null, false);
        return response;
    }
    }
    
    @Override
    public StandardResponse<Patient> savePatient(Patient patient){
        var result = patientDAO.findById((int)patient.getId());
        if(result==null){
        patientDAO.createPatient(patient);
        StandardResponse response = new StandardResponse(200, "Success", patient, true);
        return response;
        
        }
        else{
         StandardResponse response = new StandardResponse(400, "User Already Inserted", patient, false);
         return response;
        }
    }   
}
