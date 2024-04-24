package com.healthsystem.healthcaresystemapi.dao.idao;

import com.healthsystem.healthcaresystemapi.models.Person;

import java.util.List;

public interface IPersonDAO {
    Person findById(int id);

    void createPerson(Person person);

    int getNextAvailableId();

    void updatePerson(Person person);

    void deletePerson(int id);

    List<Person> getAllPersons();
}
