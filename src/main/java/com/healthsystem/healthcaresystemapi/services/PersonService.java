package com.healthsystem.healthcaresystemapi.services;

import com.healthsystem.healthcaresystemapi.dao.PersonDAO;
import com.healthsystem.healthcaresystemapi.models.Person;
import com.healthsystem.healthcaresystemapi.services.iservices.IPersonService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import lombok.var;

import java.util.List;

public class PersonService implements IPersonService {
    private final PersonDAO personDAO;

    public PersonService(){
        this.personDAO = new PersonDAO();
    }

    @Override
    public StandardResponse<Person> getPersonById(int id){
        try{
            if(personDAO.findById(id)!=null){
                var person = personDAO.findById(id);
                return new StandardResponse<>(200, "Success", person, true);

            }
            else{
                return new StandardResponse<>(400, "person not available", null, false);

            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<Person> savePerson(Person person){
        try{
            var result = personDAO.findById((int)person.getId());
            if(result==null){
                personDAO.createPerson(person);
                return new StandardResponse<>(200, "Create Success", person, true);
            }
            else{
                return new StandardResponse<>(400, "Person Already Inserted", person, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);}
    }

    @Override
    public StandardResponse<Person> removePerson(int id){
        try{
            var result = personDAO.findById(id);
            if(result!=null){
                personDAO.deletePerson(id);
                return new StandardResponse<>(200, "Remove Success", result, true);
            }
            else{
                return new StandardResponse<>(400, "User not found", result, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }
    }

    @Override
    public StandardResponse<List<Person>> getAllPersons(){
        try{
            List<Person> personList = personDAO.getAllPersons();
            return new StandardResponse<>(200, "Person list retrieve successfull", personList, true);
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), null, false);
        }
    }

    @Override
    public StandardResponse<Person> updatePerson(int id, Person person){
        try{
            var result = personDAO.findById(id);
            if(result!=null){
                personDAO.updatePerson(person);
                return new StandardResponse<>(200, "Patient update successfull", person, true);
            }
            else{
                return new StandardResponse<>(400, "Patient not available", person, false);
            }
        }
        catch(Exception ex){
            return new StandardResponse<>(400, ex.getMessage(), person, false);
        }
    }
}
