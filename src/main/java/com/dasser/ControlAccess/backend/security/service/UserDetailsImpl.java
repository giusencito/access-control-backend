package com.dasser.ControlAccess.backend.security.service;

import com.dasser.ControlAccess.backend.domain.model.entity.User;
import com.dasser.ControlAccess.backend.domain.persistence.UserRepository;
import com.dasser.ControlAccess.backend.domain.service.UserService;
import com.dasser.ControlAccess.backend.security.Principal.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("usuario ya existe"));
        return PrincipalUser.build(user);
    }
}
