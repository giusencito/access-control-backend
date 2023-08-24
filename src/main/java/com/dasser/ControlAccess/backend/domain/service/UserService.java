package com.dasser.ControlAccess.backend.domain.service;

import com.dasser.ControlAccess.backend.domain.model.entity.User;

import java.util.Optional;

public interface UserService {
    void save(User user);
    Optional<User> getbyUserName(String username);
    Boolean existsByUsername(String username);

}
