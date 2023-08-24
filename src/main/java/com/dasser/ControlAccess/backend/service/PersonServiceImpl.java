package com.dasser.ControlAccess.backend.service;

import com.dasser.ControlAccess.backend.domain.model.entity.Person;
import com.dasser.ControlAccess.backend.domain.model.entity.User;
import com.dasser.ControlAccess.backend.domain.model.enumeration.RolName;
import com.dasser.ControlAccess.backend.domain.model.enumeration.State;
import com.dasser.ControlAccess.backend.domain.persistence.PersonRepository;
import com.dasser.ControlAccess.backend.domain.persistence.UserRepository;
import com.dasser.ControlAccess.backend.domain.service.PersonService;
import com.dasser.ControlAccess.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PersonServiceImpl implements PersonService {
    private static final String ENTITY = "Artist";
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public Person update(Long userId, Person request) {
        return personRepository.findById(userId).map(person->{
            Date date = new Date();
            person.setUpdatedAt(date);
            person.setUsername((request.getUsername()));
            person.setName((request.getName()));

            person.setState(request.getState());
            User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));


            person.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setPassword(passwordEncoder.encode(request.getPassword()));



            user.setUsername(request.getUsername());

            userRepository.save(user);
            personRepository.save(person);

            return  person;
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public Person create(Person person) {
        Date date = new Date();
        person.setCreatedAt(date);
        Long id = autoIncrement();
        person.setId(id);
        List<RolName> roles = Arrays.asList(RolName.ROLE_NORMAL);
        person.setRoles(roles);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        User user= new User();
        user.setId(id);
        user.setUsername(person.getUsername());
        user.setPassword(person.getPassword());
        user.setRoles(person.getRoles());
        userRepository.save(user);
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAll() {

        return personRepository.findAll();
    }

    @Override
    public Page<Person> getAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    public List<Person> getByState(State state) {
        return personRepository.findByState(state);
    }

    @Override
    public List<Person> getByName(String name) {
        return personRepository.findByUsername(name);

    }

    @Override
    public List<Person> GetNameContains(String name) {
        return personRepository.findByNameContains(name);
    }

    private long autoIncrement() {
        List<User> users = userRepository.findAll();
        return users.isEmpty()? 1 :
                users.stream().max(Comparator.comparing(User::getId)).get().getId() + 1;
    }
}
