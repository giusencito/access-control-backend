package com.dasser.ControlAccess.backend.security.service;


import com.dasser.ControlAccess.backend.domain.model.entity.Admin;
import com.dasser.ControlAccess.backend.domain.model.entity.User;
import com.dasser.ControlAccess.backend.domain.model.enumeration.RolName;
import com.dasser.ControlAccess.backend.domain.persistence.AdminRepository;
import com.dasser.ControlAccess.backend.domain.persistence.UserRepository;
import com.dasser.ControlAccess.backend.security.Dto.LoginUser;
import com.dasser.ControlAccess.backend.security.Dto.NewAdmin;
import com.dasser.ControlAccess.backend.security.Dto.jwtDto;
import com.dasser.ControlAccess.backend.security.jwt.jwtProvider;
import com.dasser.ControlAccess.shared.exception.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    jwtProvider  jwtProvider;

    public ResponseEntity<jwtDto> login(LoginUser loginUser, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("campos mal puestos"), HttpStatus.BAD_REQUEST);

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        jwtDto jwtDto = new jwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);







    }


    public ResponseEntity<?> register(NewAdmin request, BindingResult bindingResult) throws Message {
        if (bindingResult.hasErrors()){


            return new ResponseEntity(new Message("campos mal puestos o usuario invalido"), HttpStatus.BAD_REQUEST);
        }
        if(adminRepository.existsByUsername(request.getUsername())){
            return new ResponseEntity(new Message("ya existe nombre"), HttpStatus.BAD_REQUEST);

        }
        long id =autoIncrement();
        String password = passwordEncoder.encode(request.getPassword());
        List<RolName> roles = Arrays.asList(RolName.ROL_ADMIN);
        User user= new User();
        Admin admin= new Admin();
        admin.setId(id);
        user.setId(id);
        admin.setUsername(request.getUsername());
        user.setUsername(request.getUsername());

        admin.setPassword(password);
        user.setPassword(password);

        admin.setRoles(roles);
        user.setRoles(roles);
        userRepository.save(user);
        adminRepository.save(admin);
        return new ResponseEntity(new Message("admin creado"), HttpStatus.OK
        );


    }
    private long autoIncrement() {
        List<User> users = userRepository.findAll();
        return users.isEmpty()? 1 :
                users.stream().max(Comparator.comparing(User::getId)).get().getId() + 1;
    }



















}
