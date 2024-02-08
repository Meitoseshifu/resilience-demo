package com.bobocode.persons.repository;

import com.bobocode.persons.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
