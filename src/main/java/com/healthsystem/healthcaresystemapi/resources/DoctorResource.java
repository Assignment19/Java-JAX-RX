package com.healthsystem.healthcaresystemapi.resources;

import com.healthsystem.healthcaresystemapi.models.Doctor;
import com.healthsystem.healthcaresystemapi.models.DoctorWithPerson;
import com.healthsystem.healthcaresystemapi.services.DoctorService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/doctor")
public class DoctorResource {

    private final DoctorService doctorService;

    public DoctorResource() {
        this.doctorService = new DoctorService();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<DoctorWithPerson> getDoctor(@PathParam("id") int id) {
        return doctorService.getDoctorById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<DoctorWithPerson> createDoctor(Doctor doctor) {
        return (doctorService.saveDoctor(doctor));
    }

    @GET
    @Path("/get-all-doctors")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<List<DoctorWithPerson>> getAllDoctors(){
        return doctorService.getAllDoctor();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Doctor> updateDoctor(@PathParam("id") int id, Doctor doctor) {
        return (doctorService.updateDoctor(id, doctor));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Doctor> removeDoctor(@PathParam("id") int id) {
        return (doctorService.removeDoctor(id));
    }


}
