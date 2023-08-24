package com.dasser.ControlAccess.backend.domain.model.entity;

import com.dasser.ControlAccess.backend.domain.model.enumeration.State;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "persons")
public class Person  extends User{



    @NotNull
    @NotBlank
    private String name;



    @NotNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    private State state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;


}