package com.dasser.ControlAccess.backend.api;

import com.dasser.ControlAccess.backend.domain.model.enumeration.State;
import com.dasser.ControlAccess.backend.domain.service.PersonService;
import com.dasser.ControlAccess.backend.mapping.PersonMapper;
import com.dasser.ControlAccess.backend.resource.person.CreatePersonResource;
import com.dasser.ControlAccess.backend.resource.person.PersonResource;
import com.dasser.ControlAccess.backend.resource.person.UpdatePersonResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/persons")
public class PersonController {


    @Autowired
    private PersonService personService;

    @Autowired
    private PersonMapper mapper;



    @ApiOperation(value = "Get all Users",notes = "Este consulta consiste en obtener a todos los usuarios")
    @GetMapping
    public Page<PersonResource> getAllUsers(Pageable pageable) {
        return mapper.modelListToPage(personService.getAll(), pageable);
    }
    @ApiOperation(value = "Create  an user ",notes = "Este consulta consiste en crear a un usuario mediante unos datos establecidos ")
    @PostMapping
    public PersonResource createUser(@RequestBody CreatePersonResource request) {

        return mapper.toResource(personService.create(mapper.toModel(request)));
    }

    @ApiOperation(value = "Update  an user ",notes = "Este consulta consiste en actualizar  la informacion  de un usuario ")
    @PutMapping("{userId}")
    public PersonResource updateUser(@PathVariable Long userId, @RequestBody UpdatePersonResource request) {
        return mapper.toResource(personService.update(userId, mapper.toModel(request)));
    }
    @ApiOperation(value = "Get all Users by username",notes = "Este consulta consiste en obtener  usuarios  segun el nombre de usuario")
    @GetMapping("/name/{userName}")
    public Page<PersonResource> getAllUsersbyName(@PathVariable String userName,Pageable pageable) {
        return mapper.modelListToPage(personService.getByName(userName), pageable);
    }

    @ApiOperation(value = "Get all Users by name",notes = "Este consulta consiste en obtener  usuarios  segun el estado")
    @GetMapping("/state/{userSate}")
    public Page<PersonResource> getAllUsersbyState(@PathVariable State userSate, Pageable pageable) {
        return mapper.modelListToPage(personService.getByState(userSate), pageable);
    }

    @GetMapping("/states")
    public List<State> getAllStates(){
        return Arrays.asList(State.values());
    }











}
