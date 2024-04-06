package com.sabancinuiv.cs310_project_demo.configuration;
import com.sabancinuiv.cs310_project_demo.model.User;
import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/* apileri test etmek icin sahte data yolluyor dbye. */
@Component
public class Populator {

    Logger logger = LoggerFactory.getLogger(Populator.class);

    @Autowired
    private TodoEntryRepository todoRepo;

    @Autowired
    private UserRepository userRepo;

    @PostConstruct
    public void init(){
        todoRepo.deleteAll();
        userRepo.deleteAll();

        logger.info("(DEBUG)(Populator.java) USERS AND ENTRIES COLLECTION HAS BEEN CLEARED");

        todoRepo.saveAll(entries);
        userRepo.saveAll(users);

        logger.info("(DEBUG)(Populator.java) USERS AND ENTRIES COLLECTION HAS BEEN POPULATED");
    }


    List<TodoEntry> entries = Arrays.asList(
            new TodoEntry(null, "61258", "Finish the report", "Work", "Work", true, LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
            new TodoEntry(null, "24598", "Read a book about Spring Boot", "Personal Development", "Education", false, LocalDateTime.now(), LocalDateTime.now().plusWeeks(2)),
            new TodoEntry(null, "24516", "Plan the weekly meeting agenda", "Meeting", "Work", true, LocalDateTime.now(), LocalDateTime.now().plusHours(48)),
            new TodoEntry(null, "99812", "denemedeneme", "Meeting", "Work", true, LocalDateTime.now(), LocalDateTime.now().plusHours(48))
    );


    List<User> users = Arrays.asList(
        new User(null, "kwilliams", "o%4eCld*2ac@**bqB^$_jqE3Z1)mD&Y78cl8OZ%d#eSkJb(sYuT7u!Njeyu9", "6544da6d5cf8d5041bad58136ec610201268bbf7d05131153e8daa9001a4863a", "bob62@example.com", "959-421-2225x922", LocalDateTime.parse("2024-01-12T15:35:48"), LocalDateTime.parse("2024-01-12T15:35:48"), "53.165.17.26"),
        new User(null, "jesserodriguez", "tIO)a09i@7Xk_(l80&x7H_wU$X7aymBPhPQLhvZkzx%SQt!D%sY7FWpyK2Pv", "1aa07c5bd7b1e887c5d879f48b2bb6efb27d9b4bc11aaeacfd1bfb05df4ff87f", "tsanders@example.net", "(346)643-3301x7003", LocalDateTime.parse("2024-03-23T08:24:47"), LocalDateTime.parse("2024-03-23T08:24:47"), "165.91.132.164"),
        new User(null, "kaylaandersen", "U&!36GF+IFOR+aax&XzCvuSs^GQ2!rK$*89UltB@fMrx1Oe%RWlDQaZ9Yzgy", "f380599625719e0f1e01e403c0362554eec2b1324dbf2853f9e4bb90e90e630f", "dparker@example.org", "864-723-8908x870", LocalDateTime.parse("2024-03-14T21:04:40"), LocalDateTime.parse("2024-03-14T21:04:40"), "41.1.42.17"),
        new User(null, "walkerashley", "a^izz4zS!!8W3u1#yQBtkeQ4BdVdfByi*tqKFv1#4zB9REdoF6&b5g__!IXy", "ec257eec29cf200776ccf21c7fdf6baef837531be22605fd1fcab6b381fa1b4e", "millertiffany@example.com", "615.880.9739x97418", LocalDateTime.parse("2024-03-15T01:34:32"), LocalDateTime.parse("2024-03-15T01:34:32"), "136.127.124.62"),
        new User(null, "gpeterson", "2L41s_S6LEMp7OX52SCc&rV96xeBtiLuBALf@pbIzSPPP$2_hOLL+5Fl+@ZL", "c5ae8a628cdc41479cdb010a9dff2b3947c97d149da58de3bb7cad1406b60ce4", "nathan75@example.org", "+1-928-537-4867x6229", LocalDateTime.parse("2024-03-25T11:07:23"), LocalDateTime.parse("2024-03-25T11:07:23"), "184.69.222.205")
    );


}

/* Lore
@PostConstruct annotation is used to indicate that the annotated method should be executed after dependency injection is done to perform any initialization
 */