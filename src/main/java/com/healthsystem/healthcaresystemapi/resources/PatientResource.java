package com.healthsystem.healthcaresystemapi.resources;

import com.healthsystem.healthcaresystemapi.models.Patient;
import com.healthsystem.healthcaresystemapi.services.PatientService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Path("/patients")
@Api(value = "/patients", description = "Operations about patients")
public class PatientResource {
    private final PatientService patientService;

    public PatientResource() {
        this.patientService = new PatientService();
    }
    
@GET
@Path("/{id}")
@Produces(MediaType.APPLICATION_JSON)
@ApiOperation(value = "Get patient by ID", response = Patient.class)
public StandardResponse<Patient> getPatient(@PathParam("id") int id) {
  StandardResponse<Patient> response = patientService.getPatientById(id);
  return response;
}

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create Person", response = Patient.class)
    public StandardResponse<Patient> createPatient(Patient patient) {
        return (patientService.savePatient(patient));
    }
    
   @GET
   @Path("/get-all-patients")
   @Produces(MediaType.APPLICATION_JSON)
   @ApiOperation(value = "Get all patients", response = Patient.class)
   public StandardResponse<List<Patient>> getAllPatients(){
   StandardResponse<List<Patient>> response = patientService.getAllPatients();
   return response;
   }
   
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update person", response = Patient.class)
    public StandardResponse<Patient> updatePatient(@PathParam("id") int id) {
       return (patientService.updatePatient(id));
    }
    
   @DELETE
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @ApiOperation(value = "Remove patient", response = Patient.class)
   public StandardResponse<Patient> removePatient(@PathParam("id") int id) {
   return (patientService.removePatient(id));
   }
      
}
