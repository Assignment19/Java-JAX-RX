package com.healthsystem.healthcaresystemapi.resources;

import com.healthsystem.healthcaresystemapi.models.Billing;
import com.healthsystem.healthcaresystemapi.models.BillingWithPerson;
import com.healthsystem.healthcaresystemapi.models.Doctor;
import com.healthsystem.healthcaresystemapi.models.DoctorWithPerson;
import com.healthsystem.healthcaresystemapi.services.BillingService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/billing")
public class BillingResource {
    private final BillingService billingService;


    public BillingResource() {
        this.billingService = new BillingService();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<BillingWithPerson> getBill(@PathParam("id") int id) {
        return billingService.getBillById(id);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<BillingWithPerson> createBill(Billing bill) {
        return (billingService.saveBill(bill));
    }

    @GET
    @Path("/get-all-bills")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<List<BillingWithPerson>> getAllBills(){
        return billingService.getAllBills();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Billing> updateBill(@PathParam("id") int id, Billing bill) {
        return (billingService.updateBill(id, bill));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Billing> removeBill(@PathParam("id") int id) {
        return (billingService.removeBill(id));
    }


}
