package com.healthsystem.healthcaresystemapi.resources;

import com.healthsystem.healthcaresystemapi.models.Patient;
import com.healthsystem.healthcaresystemapi.services.PatientService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("/patients")
public class PatientResource {
    private final PatientService patientService;

    public PatientResource() {
        this.patientService = new PatientService();
    }
    
   @GET
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
public StandardResponse<Patient> getPatient(@PathParam("id") int id) {
  StandardResponse<Patient> response = patientService.getPatientById(id);
  return response;
}

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<Patient> createPatient(Patient patient) {
        return (patientService.savePatient(patient));
    }
    
}
