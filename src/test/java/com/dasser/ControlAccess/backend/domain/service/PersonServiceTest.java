package com.dasser.ControlAccess.backend.domain.service;

import com.dasser.ControlAccess.backend.domain.model.entity.Person;
import com.dasser.ControlAccess.backend.domain.model.enumeration.RolName;
import com.dasser.ControlAccess.backend.domain.model.enumeration.State;
import com.dasser.ControlAccess.backend.domain.persistence.PersonRepository;
import com.dasser.ControlAccess.backend.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonServiceTest {



    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;


    @Test
    void create(){
        Date date = new Date();
        Person request = new Person();
        request.setUsername("user");
        request.setPassword("contra");
        request.setCreatedAt(date);
        request.setState(State.Activo);
        request.setRoles(Arrays.asList(RolName.ROLE_NORMAL));



        Person createdPerson = new Person();
        createdPerson.setId(1L);
        createdPerson.setUsername(request.getUsername());
        createdPerson.setPassword("contra");
        createdPerson.setCreatedAt(request.getCreatedAt());
        createdPerson.setState(request.getState());
        createdPerson.setRoles(request.getRoles());

        when(personRepository.save(any(Person.class))).thenReturn(createdPerson);

        Person result = personService.create(request);

        assertNotNull(result);
        assertEquals(request.getUsername(), result.getUsername());








    }








}