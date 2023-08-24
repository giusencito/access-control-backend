package com.dasser.ControlAccess.backend.resource.person;

import com.dasser.ControlAccess.backend.domain.model.enumeration.State;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdatePersonResource {
    private String username;
    private String name;
    private String password;
    private State state;
}
