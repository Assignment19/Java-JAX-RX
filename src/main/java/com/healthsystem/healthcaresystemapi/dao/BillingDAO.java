package com.healthsystem.healthcaresystemapi.dao;

import com.healthsystem.healthcaresystemapi.dao.idao.IBillingDAO;
import com.healthsystem.healthcaresystemapi.models.Billing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillingDAO implements IBillingDAO {
    private static final Map<Integer, Billing> billingMap = new HashMap<>();

    @Override
    public Billing findById(int id) {
        return billingMap.get(id);
    }

    @Override
    public void createBilling(Billing billing) {
        // Generate a unique ID for the patient
        int nextId = getNextAvailableId();
        billing.setId(nextId);
        billingMap.put(nextId, billing);
    }

    @Override
    public int getNextAvailableId() {
        // Find the maximum ID currently in use and increment by 1
        int maxId = billingMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxId + 1;
    }

    @Override
    public void updateBilling(Billing billing) {
        billingMap.put((int)billing.getId(), billing);
    }

    @Override
    public void deleteBilling(int id) {
        billingMap.remove(id);
    }

    @Override
    public List<Billing> getAllBilling() {
        List<Billing> allBilling = new ArrayList<>();
        for (Map.Entry<Integer, Billing> entry : billingMap.entrySet()) {
            allBilling.add(entry.getValue());
        }
        return allBilling;
    }
}
