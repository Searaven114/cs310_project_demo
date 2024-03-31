package com.sabancinuiv.cs310_project_demo;

import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import com.sabancinuiv.cs310_project_demo.model.User;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.time.LocalDateTime;

//Çalışması için sanırım mongodb collectionuna baglı olması lazım, yoksa pom.xml'den mongo modülünü comment out et
//Security modülü ekli, bu configi exlude etmezsen default login page koymaya zorluyor, ben disable ettim (k.adı = username, sifre = loglarda kendi veriyor)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Cs310ProjectDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Cs310ProjectDemoApplication.class, args);
    }

    Logger logger = LoggerFactory.getLogger(Cs310ProjectDemoApplication.class);

    @Override
    public void run(String... args) throws Exception {

        logger.info("(DEBUG)(Cs310ProjectDemoApplication.java) STARTING THE APPLICATION !");
    }
}

/*
PostConstruct and PreDestroy:
    With @PostConstruct and @PreDestroy annotations, Spring can run methods after the bean has been constructed or just before destruction.

    https://www.simplilearn.com/tutorials/spring-tutorial/spring-ioc-container#:~:text=The%20IoC%20container%20constructs%20an,create%20and%20manage%20objects%20manually.
*/


