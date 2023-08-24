package com.dasser.ControlAccess.backend.service;

import com.dasser.ControlAccess.backend.domain.model.entity.Admin;
import com.dasser.ControlAccess.backend.domain.model.entity.Person;
import com.dasser.ControlAccess.backend.domain.model.entity.User;
import com.dasser.ControlAccess.backend.domain.persistence.AdminRepository;
import com.dasser.ControlAccess.backend.domain.persistence.UserRepository;
import com.dasser.ControlAccess.backend.domain.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void save(Admin admin) {
        Long id = autoIncrement();
        admin.setId(id);
        adminRepository.save(admin);
    }

    @Override
    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }

    private long autoIncrement() {
        List<User> users = userRepository.findAll();
        return users.isEmpty()? 1 :
                users.stream().max(Comparator.comparing(User::getId)).get().getId() + 1;
    }
}
