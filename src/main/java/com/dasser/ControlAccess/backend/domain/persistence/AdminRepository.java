package com.dasser.ControlAccess.backend.domain.persistence;

import com.dasser.ControlAccess.backend.domain.model.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,Long> {
    Boolean existsByUsername(String username);
}
