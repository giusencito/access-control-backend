package com.dasser.ControlAccess.backend.resource.person;


import com.dasser.ControlAccess.backend.domain.model.enumeration.State;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonResource {
    private Long id;
    private String username;
    private String name;
    private String password;
    private State state;
    private Date createdAt;
    private Date updatedAt;
}
