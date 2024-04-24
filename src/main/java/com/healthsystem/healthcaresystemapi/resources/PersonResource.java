package com.healthsystem.healthcaresystemapi.resources;

import com.healthsystem.healthcaresystemapi.models.Person;
import com.healthsystem.healthcaresystemapi.services.PersonService;
import com.healthsystem.healthcaresystemapi.utility.StandardResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonResource {

    private final PersonService personService;

    public PersonResource() {
        this.personService = new PersonService();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<Person> createPerson(Person person) {
        return (personService.savePerson(person));
    }

    @GET
    @Path("/get-all-persons")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StandardResponse<List<Person>> getAllPersons(){
        return personService.getAllPersons();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Person> updatePerson(@PathParam("id") int id, Person person) {
        return (personService.updatePerson(id, person));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Person> getPerson(@PathParam("id") int id) {
        return personService.getPersonById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StandardResponse<Person> removePerson(@PathParam("id") int id) {
        return (personService.removePerson(id));
    }

}
