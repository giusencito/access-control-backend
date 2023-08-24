package com.dasser.ControlAccess.backend.resource.person;


import com.dasser.ControlAccess.backend.domain.model.enumeration.State;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePersonResource {
    private String username;
    private String name;
    private String password;
    private State state;
}

