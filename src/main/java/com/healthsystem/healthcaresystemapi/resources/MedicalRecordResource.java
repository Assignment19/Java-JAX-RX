package com.healthsystem.healthcaresystemapi.resources;

import com.healthsystem.healthcaresystemapi.models.MedicalRecord;
import com.healthsystem.healthcaresystemapi.models.MedicalRecordWithPatient;
import com.healthsystem.healthcaresystemapi.services.MedicalRecordService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/medicalrecord")
public class MedicalRecordResource {


    private final MedicalRecordService medicalRecordService;

    public MedicalRecordResource() {
        this.medicalRecordService = new MedicalRecordService();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<MedicalRecordWithPatient> getMedicalRecord(@PathParam("id") int id) {
        return medicalRecordService.getMedicalRecordById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<MedicalRecordWithPatient> createMedicalRecord(MedicalRecord medicalRecord) {
        return (medicalRecordService.saveMedicalRecord(medicalRecord));
    }

    @GET
    @Path("/get-all-medical-records")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<List<MedicalRecordWithPatient>> getAllMedicalRecord(){
        return medicalRecordService.getAllMedicalRecord();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<MedicalRecord> updateMedicalRecord(@PathParam("id") int id, MedicalRecord medicalRecord) {
        return (medicalRecordService.updateMedicalRecord(id, medicalRecord));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<MedicalRecord> removeMedicalRecord(@PathParam("id") int id) {
        return (medicalRecordService.removeMedicalRecord(id));
    }

}
