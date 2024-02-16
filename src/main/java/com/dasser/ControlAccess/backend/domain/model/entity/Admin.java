package com.dasser.ControlAccess.backend.domain.model.entity;


import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;




@Getter
@Setter
@With
@Entity
@Document(collection = "admins")
public class Admin extends User{





}
