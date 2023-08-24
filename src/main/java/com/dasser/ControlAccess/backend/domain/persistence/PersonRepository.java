package com.dasser.ControlAccess.backend.domain.persistence;

import com.dasser.ControlAccess.backend.domain.model.entity.Person;
import com.dasser.ControlAccess.backend.domain.model.enumeration.State;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person,Long> {
    List<Person>findByUsername(String username);
    List<Person>findByNameContains(String name);
    List<Person>findByState(State state);


}
