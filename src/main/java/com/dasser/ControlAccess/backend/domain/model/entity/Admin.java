package com.dasser.ControlAccess.backend.domain.model.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@With
@Entity
@Document(collection = "admins")
public class Admin extends User{





}
