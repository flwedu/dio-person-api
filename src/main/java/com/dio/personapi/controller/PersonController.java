package com.dio.personapi.controller;

import com.dio.personapi.model.Person;
import com.dio.personapi.repository.PersonRepository;
import com.dio.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getListPerson(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        Optional<Person> findPerson = service.findById(id);

        if(findPerson.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(findPerson.get());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return ResponseEntity.ok(service.save(person));
    }
}
