package com.example.apidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiDemoApplication implements CommandLineRunner {

    @Autowired
    BlogRepo repo;

    public static void main(String[] args) {
        SpringApplication.run(ApiDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        repo.save(new Blog("First Tri Mester", "Is the easiset"));
        repo.save(new Blog("First Tri Mester", "Is the easiset"));
        repo.save(new Blog("First Tri Mester", "Is the easiset"));
        repo.save(new Blog("First Tri Mester", "Is the easiset"));
        repo.save(new Blog("First Tri Mester", "Is the easiset"));
    }
}
