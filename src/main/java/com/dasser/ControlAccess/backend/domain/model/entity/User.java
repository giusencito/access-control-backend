package com.dasser.ControlAccess.backend.domain.model.entity;

import com.dasser.ControlAccess.backend.domain.model.enumeration.RolName;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



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
