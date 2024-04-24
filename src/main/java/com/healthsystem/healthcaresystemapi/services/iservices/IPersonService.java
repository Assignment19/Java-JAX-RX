package com.healthsystem.healthcaresystemapi.services.iservices;

import com.healthsystem.healthcaresystemapi.models.Person;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;

import java.util.List;

public interface IPersonService {

    StandardResponse<Person> getPersonById(int id);

    StandardResponse<Person> savePerson(Person person);

    StandardResponse<Person> removePerson(int id);

    StandardResponse<List<Person>> getAllPersons();

    StandardResponse<Person> updatePerson(int id, Person person);
}
