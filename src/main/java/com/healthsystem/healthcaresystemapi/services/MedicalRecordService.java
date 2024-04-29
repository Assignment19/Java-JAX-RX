package com.healthsystem.healthcaresystemapi.services;

import com.healthsystem.healthcaresystemapi.dao.*;
import com.healthsystem.healthcaresystemapi.models.MedicalRecord;
import com.healthsystem.healthcaresystemapi.models.MedicalRecordWithPatient;
import com.healthsystem.healthcaresystemapi.services.iservices.IMedicalRecordService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import lombok.var;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService implements IMedicalRecordService {
    private final DoctorDAO doctorDAO;
    private final PersonDAO personDAO;
    private final PatientDAO patientDAO;
    private final MedicalRecordDAO medicalRecordDAO;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public MedicalRecordService(){
        this.doctorDAO = new DoctorDAO();
        this.personDAO = new PersonDAO();
        this.doctorService = new DoctorService();
        this.patientService = new PatientService();
        this.medicalRecordDAO = new MedicalRecordDAO();
        this.patientDAO = new PatientDAO();
    }

    @Override
    public StandardResponse<MedicalRecordWithPatient> getMedicalRecordById(int id){
        try{
            var medicalRecord = medicalRecordDAO.findById(id);
            if(medicalRecord!=null){
                var patientId = medicalRecord.getPatientId();
                var patient = patientService.getPatientById((int)patientId).data;
                if(patient!=null){
                    MedicalRecordWithPatient medicalRecordWithPatient = new MedicalRecordWithPatient(medicalRecord.getId(),medicalRecord.getDiseases(),
                            medicalRecord.getTreatments(), patient);
                    return new StandardResponse<>(200, "Medical Record retrieve Success", medicalRecordWithPatient, true);}

                else{
                    return new StandardResponse<>(404, "Patient not available", null, true);

                }

            }
            else{
                return new StandardResponse<>(404, "Medical Record is not available", null, false);

            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<MedicalRecordWithPatient> saveMedicalRecord(MedicalRecord medicalRecord){
        try{
            var patientId = medicalRecord.getPatientId();
            var patient = patientService.getPatientById((int)patientId).getData();
            if(patient!=null){
                var result = medicalRecordDAO.findById((int)medicalRecord.getId());
                if(result==null){
                    medicalRecordDAO.createMedicalRecord(medicalRecord);
                    MedicalRecordWithPatient medicalRecordWithPatient = new MedicalRecordWithPatient(medicalRecord.getId(),medicalRecord.getDiseases(),
                            medicalRecord.getTreatments(),patient);

                    return new StandardResponse<>(200, "Medical record Create Success", medicalRecordWithPatient, true);

                }
                else{
                    return new StandardResponse<>(404, "Medical record Already Inserted", null, false);

                }}
            else{
                return new StandardResponse<>(404, "Patient and Doctor not available", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<MedicalRecord> removeMedicalRecord(int id){
        try{
            var result = medicalRecordDAO.findById(id);
            if(result!=null){
                medicalRecordDAO.deleteMedicalRecord(id);
                return new StandardResponse<>(200, "Medical Record Remove Success", result, true);
            }

            else{
                return new StandardResponse<>(404, "Medical record not found", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);
        }

    }

    @Override
    public StandardResponse<MedicalRecord> updateMedicalRecord(int id, MedicalRecord medicalRecord){
        try{
            var result = medicalRecordDAO.findById(id);
            if(result!=null){
                medicalRecordDAO.updateMedicalRecord(medicalRecord);
                return new StandardResponse<>(200, "Medical Record update successfully", medicalRecord, true);
            }
            else{
                return new StandardResponse<>(404, "Medical record not available", medicalRecord, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);
        }
    }

    @Override
    public StandardResponse<List<MedicalRecordWithPatient>> getAllMedicalRecord(){
        try{
            List<MedicalRecordWithPatient> userList = new ArrayList<>();
            List<MedicalRecord> medicalRecordList = medicalRecordDAO.getAllMedicalRecord();
            for(int i = 0; i<medicalRecordList.size(); i++){
                var patientId = medicalRecordList.get(i).getPatientId();
                var patient = patientService.getPatientById((int)patientId).getData();
                MedicalRecordWithPatient medicalRecordWithPatient = new MedicalRecordWithPatient(medicalRecordList.get(i).getId(), medicalRecordList.get(i).getDiseases(),
                        medicalRecordList.get(i).getTreatments(),patient );
                userList.add(i, medicalRecordWithPatient);
            }
            return new StandardResponse<>(200, "Medical record list retrieve successfully", userList, true);
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);
        }
    }


}
