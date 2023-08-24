package com.dasser.ControlAccess.backend.domain.model.entity;

import com.dasser.ControlAccess.backend.domain.model.enumeration.RolName;
import lombok.*;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private Long id;
    @NotNull
    @NotBlank
    @Indexed(unique = true)
    private String username;
    @NotNull
    @NotBlank
    private String password;


    private List<RolName> roles;





}
