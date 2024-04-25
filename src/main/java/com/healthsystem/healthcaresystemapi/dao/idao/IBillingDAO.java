package com.healthsystem.healthcaresystemapi.dao.idao;

import com.healthsystem.healthcaresystemapi.models.Billing;

import java.util.List;

public interface IBillingDAO {
    Billing findById(int id);

    void createBilling(Billing billing);

    int getNextAvailableId();

    void updateBilling(Billing billing);

    void deleteBilling(int id);

    List<Billing> getAllBilling();
}
