package com.healthsystem.healthcaresystemapi.services;
import com.healthsystem.healthcaresystemapi.dao.BillingDAO;
import com.healthsystem.healthcaresystemapi.dao.PersonDAO;
import com.healthsystem.healthcaresystemapi.models.Billing;
import com.healthsystem.healthcaresystemapi.models.BillingWithPerson;
import com.healthsystem.healthcaresystemapi.services.iservices.IBillingService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import lombok.var;

import java.util.ArrayList;
import java.util.List;

public class BillingService implements IBillingService {
    private final PersonDAO personDAO;
    private final BillingDAO billingDAO;

    public BillingService(){
        this.personDAO = new PersonDAO();
        this.billingDAO = new BillingDAO();
    }

    @Override
    public StandardResponse<BillingWithPerson> getBillById(int id) {
        try{
            var bill = billingDAO.findById(id);
            if(bill!=null){
                var personId = bill.getPersonId();
                var person = personDAO.findById((int)personId);
                if(person!=null){

                    BillingWithPerson billingWithPerson = new BillingWithPerson(
                            bill.getId(),bill.getDescription(),bill.getPrice(),bill.getBalance(),person
                    );
                    return new StandardResponse<>(200, "Success", billingWithPerson, true);}

                else{
                    return new StandardResponse<>(404, "Person not available", null, true);

                }

            }
            else{
                return new StandardResponse<>(404, "bill not available", null, false);

            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);}
    }

    public StandardResponse<BillingWithPerson> saveBill(Billing billing){
        try{
            var personId = billing.getPersonId();
            var person = personDAO.findById((int)personId);
            if(person!=null){
                var result = billingDAO.findById((int)billing.getId());
                if(result==null){
                    billingDAO.createBilling(billing);
                    BillingWithPerson billingWithPerson = new BillingWithPerson();
                    billingWithPerson.setPerson(person);
                    billingWithPerson.setDescription(billing.getDescription());
                    billingWithPerson.setId(billing.getId());
                    billingWithPerson.setPrice(billing.getPrice());
                    billingWithPerson.setBalance(billing.getBalance());
                    return new StandardResponse<>(200, "Create Success", billingWithPerson, true);

                }
                else{
                    return new StandardResponse<>(404, "Bill Already Inserted", null, false);

                }}
            else{
                return new StandardResponse<>(404, "Person not available", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);}
    }
    @Override
    public StandardResponse<Billing> removeBill(int id){
        try{
            var result = billingDAO.findById(id);
            if(result!=null){
                billingDAO.deleteBilling(id);
                return new StandardResponse<>(200, "Remove Success", result, true);
            }
            else{
                return new StandardResponse<>(404, "Bill not found", null, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);
        }
    }

    @Override
    public StandardResponse<List<BillingWithPerson>> getAllBills(){
        try{
            List<BillingWithPerson> billListWithPerson = new ArrayList<>();
            List<Billing> billList = billingDAO.getAllBilling();
            for(int i = 0; i<billList.size(); i++){
                BillingWithPerson billingWithPerson = new BillingWithPerson();
                var bill = billList.get(i);
                var personId = bill.getPersonId();
                var person = personDAO.findById((int)personId);
                billingWithPerson.setId(bill.getId());
                billingWithPerson.setPerson(person);
                billingWithPerson.setDescription(bill.getDescription());
                billingWithPerson.setPrice(bill.getPrice());
                billingWithPerson.setBalance(bill.getBalance());
                billListWithPerson.add(i, billingWithPerson);
            }
            return new StandardResponse<>(200, "Billing list retrieve successfully", billListWithPerson, true);
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), null, false);
        }
    }

    @Override
    public StandardResponse<Billing> updateBill(int id, Billing billing){
        try{
            var result = billingDAO.findById(id);
            if(result!=null){
                billingDAO.updateBilling(billing);
                return new StandardResponse<>(200, "Bill update successfull", billing, true);
            }
            else{
                return new StandardResponse<>(404, "Bill not available", billing, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(500, ex.getMessage(), billing, false);
        }
    }

}
