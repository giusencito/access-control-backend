package com.dasser.ControlAccess.backend.security.Dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LoginUser {
    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
