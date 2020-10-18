package com.elarslan.springcachemechanism;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class CacheMechanismApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CacheMechanismApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8182"));
        app.run(args);

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
