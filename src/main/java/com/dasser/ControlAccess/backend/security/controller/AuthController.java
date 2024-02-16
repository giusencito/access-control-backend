package com.dasser.ControlAccess.backend.security.controller;

import com.dasser.ControlAccess.backend.security.Dto.LoginUser;
import com.dasser.ControlAccess.backend.security.Dto.NewAdmin;
import com.dasser.ControlAccess.backend.security.Dto.jwtDto;
import com.dasser.ControlAccess.backend.security.service.AuthService;
import com.dasser.ControlAccess.shared.exception.Message;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value="login",notes = "Esta consulta nos ayuda a logear a un admin ya registrado, en el caso de que se utilice un admin no registrado saldria error")
    @PostMapping("/login")
    public ResponseEntity<jwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){

        return authService.login(loginUser,bindingResult);
    }
    @ApiOperation(value="registerAdmin",notes = "Esta consulta nos ayuda a registrar a un admin")
    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody NewAdmin request, BindingResult bindingResult) throws Message {

        return  authService.register(request,bindingResult);


    }











}
