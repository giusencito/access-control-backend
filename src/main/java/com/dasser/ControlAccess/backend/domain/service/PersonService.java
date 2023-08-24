package com.dasser.ControlAccess.backend.domain.service;

import com.dasser.ControlAccess.backend.domain.model.entity.Person;
import com.dasser.ControlAccess.backend.domain.model.enumeration.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {
    Person update(Long userId, Person request);
    Person create(Person user);
    List<Person> getAll();
    Page<Person> getAll(Pageable pageable);
    List<Person> getByState(State state);
    List<Person> getByName(String name);
    List<Person>GetNameContains(String name);

}
