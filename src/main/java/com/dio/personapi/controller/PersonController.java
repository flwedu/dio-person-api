package com.dio.personapi.controller;

import com.dio.personapi.dto.request.PersonDTO;
import com.dio.personapi.exception.PersonNotFoundException;
import com.dio.personapi.message.MessageResponse;
import com.dio.personapi.model.Person;
import com.dio.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService service) {
        this.personService = service;
    }

    @GetMapping
    public List<PersonDTO> getListPerson(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.save(personDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) throws PersonNotFoundException {
        personService.deleteById(id);
    }
}
