package com.dasser.ControlAccess.backend.domain.service;

import com.dasser.ControlAccess.backend.domain.model.entity.Admin;

public interface AdminService {
    void save(Admin artist);
    boolean existsByUsername(String username);
}
