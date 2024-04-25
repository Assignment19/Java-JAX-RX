package com.healthsystem.healthcaresystemapi.resources;

import com.healthsystem.healthcaresystemapi.models.Prescription;
import com.healthsystem.healthcaresystemapi.models.PrescriptionWithPatientAndDoctor;
import com.healthsystem.healthcaresystemapi.services.PrescriptionService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/prescription")
public class PrescriptionResource {

    private final PrescriptionService prescriptionService;

    public PrescriptionResource() {
        this.prescriptionService = new PrescriptionService();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<PrescriptionWithPatientAndDoctor> getPrescription(@PathParam("id") int id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<PrescriptionWithPatientAndDoctor> createPrescription(Prescription prescription) {
        return (prescriptionService.savePrescription(prescription));
    }

    @GET
    @Path("/get-all-prescriptions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<List<PrescriptionWithPatientAndDoctor>> getAllPrescription(){
        return prescriptionService.getAllPrescriptions();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Prescription> updatePriscription(@PathParam("id") int id, Prescription prescription) {
        return (prescriptionService.updatePrescription(id, prescription));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Prescription> removePrescription(@PathParam("id") int id) {
        return (prescriptionService.removePrescription(id));
    }


}
