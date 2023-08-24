package com.dasser.ControlAccess.backend.security.service;

import com.dasser.ControlAccess.backend.domain.model.entity.User;
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
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> value=userService.getbyUserName(username);
        if (value.isPresent()){
            return PrincipalUser.build(value.get());
        }
        throw new UsernameNotFoundException("Error");
    }
}
