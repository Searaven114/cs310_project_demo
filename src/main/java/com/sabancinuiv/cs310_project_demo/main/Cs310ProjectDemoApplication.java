package com.sabancinuiv.cs310_project_demo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import java.time.LocalDateTime;

//Çalışması için sanırım mongodb ye baglı olması lazım, yoksa pom.xml'den mongo modülünü comment out et
//Security modülü ekli, bu configi exlude etmezsen default login page koymaya zorluyor, ben disable ettim (k.adı = username, sifre = loglarda kendi veriyor)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Cs310ProjectDemoApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(Cs310ProjectDemoApplication.class);

    private final TodoEntryRepository todoRepo;

    @Autowired
    public Cs310ProjectDemoApplication(TodoEntryRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

//  Application context yerine constructor dependency injection ile yaptım
//  @Autowired
//  ApplicationContext ctx;
//  Olayı(önemli):
//    By using constructor injection, you allow Spring to automatically inject the TodoEntryRepository when Cs310ProjectDemoApplication is created.
//    This way, you don't need to manually retrieve the bean from the ApplicationContext, and you can use the repository to save your TodoEntry objects to the database.
//  https://www.simplilearn.com/tutorials/spring-tutorial/spring-ioc-container#:~:text=The%20IoC%20container%20constructs%20an,create%20and%20manage%20objects%20manually.

    public static void main(String[] args) {
        SpringApplication.run(Cs310ProjectDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //Conditional code running flag
        final boolean DEBUG = true;

        logger.info("STARTING !");

        //TodoEntryRepository todoRepo = ctx.getBean(TodoEntryRepository.class);    //contextden todorepoyu'i çekiyor, ama biz constructor injection ile yaptık (to be deleted)

        TodoEntry entry1 = new TodoEntry(
                123123123, // id
                42, // UserId
                "Finish the report", // content
                "Work", // title
                "Work", // category
                true, // status
                LocalDateTime.now(), // createDate
                LocalDateTime.now().plusDays(1) // dueDate
        );

        TodoEntry entry2 = new TodoEntry(
                12121212, // id
                43, // UserId
                "Read a book about Spring Boot", // content
                "Personal Development", // title
                "Education", // category
                false, // status
                LocalDateTime.now(), // createDate
                LocalDateTime.now().plusWeeks(2) // dueDate
        );

        TodoEntry entry3 = new TodoEntry(
                13131313, // id
                44, // UserId
                "Plan the weekly meeting agenda", // content
                "Meeting", // title
                "Work", // category
                true, // status
                LocalDateTime.now(), // createDate
                LocalDateTime.now().plusHours(48) // dueDate
        );

        if (DEBUG) {
            System.out.println("(DEBUG) entry1: " + entry1.toString());
            System.out.println("(DEBUG) entry2: " + entry2.toString());
            System.out.println("(DEBUG) entry3: " + entry3.toString());
        }

        //Sending to db for demo purposes
        todoRepo.save(entry1);
        todoRepo.save(entry2);
        todoRepo.save(entry3);

    }
}

//TODO, PostConstruct ve PreDestroy ile detaylı debug ve action ekle.
/*
PostConstruct and PreDestroy:
    With @PostConstruct and @PreDestroy annotations, Spring can run methods after the bean has been constructed or just before destruction.
*/

// TODO, paket sistemi yamuk çalışıyor, package by layer yaptıgımda main hiyerarşide üste geçemiyor. Bu yüzden her .java aynı yerde