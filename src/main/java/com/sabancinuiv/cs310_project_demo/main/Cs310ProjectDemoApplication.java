package com.sabancinuiv.cs310_project_demo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Cs310ProjectDemoApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(Cs310ProjectDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Cs310ProjectDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("deneme");

    }
}
