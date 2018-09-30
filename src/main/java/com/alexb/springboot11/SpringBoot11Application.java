package com.alexb.springboot11;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringBoot11Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot11Application.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

    }
}
