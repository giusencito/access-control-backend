package com.dasser.ControlAccess.backend.security.Dto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class NewAdmin {
    @NotBlank
    private String username;
    @NotBlank
    private String password;





}
