package com.healthsystem.healthcaresystemapi.services;

import com.healthsystem.healthcaresystemapi.dao.*;
import com.healthsystem.healthcaresystemapi.models.Prescription;
import com.healthsystem.healthcaresystemapi.models.PrescriptionWithPatientAndDoctor;
import com.healthsystem.healthcaresystemapi.services.iservices.IPrescriptionService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import lombok.var;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionService implements IPrescriptionService {
    private final DoctorDAO doctorDAO;
    private final PatientDAO patientDAO;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final PrescriptionDAO prescriptionDAO;


    public PrescriptionService(){
        this.doctorDAO = new DoctorDAO();
        this.doctorService = new DoctorService();
        this.patientService = new PatientService();
        this.patientDAO = new PatientDAO();
        this.prescriptionDAO = new PrescriptionDAO();

    }


    @Override
    public StandardResponse<PrescriptionWithPatientAndDoctor> getPrescriptionById(int id){
        try{
            var prescription = prescriptionDAO.findById(id);
            if(prescription!=null){
                var doctorId = prescription.getDoctorID();
                var patientId = prescription.getPatientId();
                var patient = patientService.getPatientById((int)patientId).data;
                var doctor = doctorService.getDoctorById((int)doctorId).data;
                if(patient!=null&&doctor!=null){
                    PrescriptionWithPatientAndDoctor prescriptionWithPatientAndDoctor = new PrescriptionWithPatientAndDoctor(prescription.getId(),prescription.getDate(),
                            prescription.getMedication(),prescription.getDiagnoses(),patient, doctor );
                    return new StandardResponse<>(200, "Prescription retrieve Success", prescriptionWithPatientAndDoctor, true);}

                else{
                    return new StandardResponse<>(400, "Person or Doctor not available", null, true);

                }

            }
            else{
                return new StandardResponse<>(400, "Prescription not available", null, false);

            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<PrescriptionWithPatientAndDoctor> savePrescription(Prescription prescription){
        try{
            var patientId = prescription.getPatientId();
            var doctorId = prescription.getDoctorID();
            var patient = patientService.getPatientById((int)patientId).getData();
            var doctor = doctorService.getDoctorById((int)doctorId).getData();
            if(patient!=null&&doctor!=null){
                var result = prescriptionDAO.findById((int)prescription.getId());
                if(result==null){
                    prescriptionDAO.createPrescription(prescription);
                    PrescriptionWithPatientAndDoctor prescriptionWithPatientAndDoctor = new PrescriptionWithPatientAndDoctor(prescription.getId(),prescription.getDate(),
                            prescription.getMedication(),prescription.getDiagnoses(),patient, doctor );

                    return new StandardResponse<>(200, "Prescription Create Success", prescriptionWithPatientAndDoctor, true);

                }
                else{
                    return new StandardResponse<>(400, "Prescription Already Inserted", null, false);

                }}
            else{
                return new StandardResponse<>(400, "Person and Doctor not available", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<Prescription> removePrescription(int id){
        try{
            var result = prescriptionDAO.findById(id);
            if(result!=null){
                prescriptionDAO.deletePrescription(id);
                return new StandardResponse<>(200, "Prescription Remove Success", result, true);
            }

            else{
                return new StandardResponse<>(400, "Prescription is not found", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }

    }

    @Override
    public StandardResponse<Prescription> updatePrescription(int id, Prescription prescription){
        try{
            var result = prescriptionDAO.findById(id);
            if(result!=null){
                var patient = patientService.getPatientById((int)prescription.getPatientId());
                var doctor = doctorService.getDoctorById((int)prescription.getDoctorID());
                if(patient!=null && doctor!=null){
                prescriptionDAO.updatePrescription(prescription);
                return new StandardResponse<>(200, "Prescription update successfull", prescription, true);}
                else{
                    return new StandardResponse<>(200, "Patient or Doctor not available to update", null, true);

                }
            }
            else{
                return new StandardResponse<>(400, "Prescription not available", prescription, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }
    }

    @Override
    public StandardResponse<List<PrescriptionWithPatientAndDoctor>> getAllPrescriptions(){
        try{
            List<PrescriptionWithPatientAndDoctor> userList = new ArrayList<>();
            List<Prescription> prescriptionList = prescriptionDAO.getAllPrescription();
            for(int i = 0; i<prescriptionList.size(); i++){
                var doctorId = prescriptionList.get(i).getDoctorID();
                var patientId = prescriptionList.get(i).getPatientId();
                var doctor = doctorService.getDoctorById((int)doctorId).getData();
                var patient = patientService.getPatientById((int)patientId).getData();
                PrescriptionWithPatientAndDoctor prescriptionWithPatientAndDoctor = new PrescriptionWithPatientAndDoctor(prescriptionList.get(i).getId(),prescriptionList.get(i).getDate(),
                        prescriptionList.get(i).getMedication(),prescriptionList.get(i).getDiagnoses(),patient, doctor );
                userList.add(i, prescriptionWithPatientAndDoctor);
            }
            return new StandardResponse<>(200, "Prescription list retrieve successfully", userList, true);
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }
    }

}
