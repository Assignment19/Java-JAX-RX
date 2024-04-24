package com.healthsystem.healthcaresystemapi.dao;

import com.healthsystem.healthcaresystemapi.dao.idao.IPersonDAO;
import com.healthsystem.healthcaresystemapi.models.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDAO implements IPersonDAO {

    private static final Map<Integer, Person> personMap = new HashMap<>();

    @Override
    public Person findById(int id) {
        return personMap.get(id);
    }

    @Override
    public void createPerson(Person person) {
        // Generate a unique ID for the patient
        int nextId = getNextAvailableId();
        person.setId(nextId);
        // Add the patient to the map with the new ID
        personMap.put(nextId, person);
    }

    @Override
    public int getNextAvailableId() {
        // Find the maximum ID currently in use and increment by 1
        int maxId = personMap.keySet().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxId + 1;
    }

    @Override
    public void updatePerson(Person person) {
        personMap.put((int)person.getId(), person);
    }

    @Override
    public void deletePerson(int id) {
        personMap.remove(id);
    }

    @Override
    public List<Person> getAllPersons() {
        List<Person> allPersons = new ArrayList<>();
        for (Map.Entry<Integer, Person> entry : personMap.entrySet()) {
            allPersons.add(entry.getValue());
        }
        return allPersons;
    }

}
