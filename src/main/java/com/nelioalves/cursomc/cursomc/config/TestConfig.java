package com.nelioalves.cursomc.cursomc.config;

import com.nelioalves.cursomc.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    DBService service;

    @Bean
    public boolean instantiateDatabase() throws Exception {
        //service.instantiateDatabase();
        return true;
    }


}
