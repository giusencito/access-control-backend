package com.dasser.ControlAccess.backend.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration("AccessControlConfiguration")
public class MappingConfiguration {
    @Bean
    public PersonMapper personMapper() {
        return new PersonMapper();
    }
}
