package com.dasser.ControlAccess.backend.service;


import com.dasser.ControlAccess.backend.domain.model.entity.User;
import com.dasser.ControlAccess.backend.domain.persistence.UserRepository;
import com.dasser.ControlAccess.backend.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        Long id = autoIncrement();
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public Optional<User> getbyUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);

    }
    private long autoIncrement() {
        List<User> users = userRepository.findAll();
        return users.isEmpty()? 1 :
                users.stream().max(Comparator.comparing(User::getId)).get().getId() + 1;
    }
}
