package com.dasser.ControlAccess.backend.security.Dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

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
