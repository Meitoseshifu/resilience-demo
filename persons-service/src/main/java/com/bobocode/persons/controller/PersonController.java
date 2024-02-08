package com.bobocode.persons.controller;

import com.bobocode.persons.domain.Person;
import com.bobocode.persons.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {
    private static int DELAY = 500;
    private final PersonRepository personRepository;

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    /*@GetMapping("/{id}")
    public Person getOne(@PathVariable  Long id) {
        return personRepository.findById(id).orElseThrow();
    }*/

    @SneakyThrows
    @GetMapping("/{id}")
    public Person getOne(@PathVariable  Long id) {
        /*log.info("Waiting {} ms", DELAY);
        Thread.sleep(DELAY += 50);
        log.info("Responding with error ");*/
        throw new RuntimeException("Unexpected error");
        //return personRepository.findById(id).orElseThrow();
    }
}
