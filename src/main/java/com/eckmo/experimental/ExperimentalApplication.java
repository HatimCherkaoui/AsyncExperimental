package com.eckmo.experimental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExperimentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExperimentalApplication.class, args);
    }

}
