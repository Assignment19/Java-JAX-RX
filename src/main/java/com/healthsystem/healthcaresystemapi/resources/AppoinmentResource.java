package com.healthsystem.healthcaresystemapi.resources;

import com.healthsystem.healthcaresystemapi.models.AppoinmentWithDoctorAndPatient;
import com.healthsystem.healthcaresystemapi.models.Appointment;
import com.healthsystem.healthcaresystemapi.services.AppoinmentService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/appointment")
public class AppoinmentResource {

    private final AppoinmentService appoinmentService;

    public AppoinmentResource() {
        this.appoinmentService = new AppoinmentService();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<AppoinmentWithDoctorAndPatient> getAppoinment(@PathParam("id") int id) {
        return appoinmentService.getAppoinmentById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<AppoinmentWithDoctorAndPatient> createAppoinment(Appointment appointment) {
        return (appoinmentService.saveAppoinment(appointment));
    }

    @GET
    @Path("/get-all-appointments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<List<AppoinmentWithDoctorAndPatient>> getAllAppoinment(){
        return appoinmentService.getAllAppoinment();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Appointment> updateAppoinment(@PathParam("id") int id, Appointment appointment) {
        return (appoinmentService.updateAppoinment(id, appointment));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Appointment> removeAppoinment(@PathParam("id") int id) {
        return (appoinmentService.removeAppoinment(id));
    }


}
