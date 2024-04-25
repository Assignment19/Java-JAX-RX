package com.healthsystem.healthcaresystemapi.resources;
import com.healthsystem.healthcaresystemapi.models.Patient;
import com.healthsystem.healthcaresystemapi.models.PatientWithPerson;
import com.healthsystem.healthcaresystemapi.services.PatientService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.healthsystem.healthcaresystemapi.utility.Async;


@Path("/patient")
@Api(value = "/patients", description = "Operations about patients")
public class PatientResource {
    private final PatientService patientService;

    public PatientResource() {
        this.patientService = new PatientService();
    }
    
@GET
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
public StandardResponse<PatientWithPerson> getPatient(@PathParam("id") int id) {
  return patientService.getPatientById(id);
}

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<PatientWithPerson> createPatient(Patient patient) {
        return (patientService.savePatient(patient));
    }
    
   @GET
   @Path("/get-all-patients")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public StandardResponse<List<PatientWithPerson>> getAllPatients(){
   return patientService.getAllPatients();
   }


    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Patient.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Response getAllPatient() {
        StandardResponse<List<PatientWithPerson>> response = patientService.getAllPatients();
        if (response.isSuccess()) {
            return Response.ok(response.getData()).build();
        } else {
            return Response.serverError().entity(response.getMessage()).build();
        }
    }
   
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Patient> updatePatient(@PathParam("id") int id, Patient patient) {
       return (patientService.updatePatient(id, patient));
    }
    
   @DELETE
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public StandardResponse<Patient> removePatient(@PathParam("id") int id) {
   return (patientService.removePatient(id));
   }
      
}
