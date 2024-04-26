package com.healthsystem.healthcaresystemapi.services.iservices;

import com.healthsystem.healthcaresystemapi.models.Billing;
import com.healthsystem.healthcaresystemapi.models.BillingWithPerson;
import com.healthsystem.healthcaresystemapi.models.DoctorWithPerson;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import java.util.List;

public interface IBillingService {

    StandardResponse<BillingWithPerson> getBillById(int id);
    StandardResponse<BillingWithPerson> saveBill(Billing billing);
    StandardResponse<Billing> removeBill(int id);
    StandardResponse<List<BillingWithPerson>> getAllBills();
    StandardResponse<Billing> updateBill(int id, Billing billing);
}
