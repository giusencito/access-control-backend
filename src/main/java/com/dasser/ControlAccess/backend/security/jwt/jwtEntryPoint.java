package com.dasser.ControlAccess.backend.security.jwt;
import com.dasser.ControlAccess.shared.exception.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class jwtEntryPoint implements AuthenticationEntryPoint{
    private final static Logger logger= LoggerFactory.getLogger(jwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("fail en el metodo commence");
        Message message = new Message("token invalido o vacio");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(message));
        response.getWriter().flush();
        response.getWriter().close();


    }
}
