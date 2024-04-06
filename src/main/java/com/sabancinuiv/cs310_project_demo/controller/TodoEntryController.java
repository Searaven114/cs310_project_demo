package com.sabancinuiv.cs310_project_demo.controller;

import com.sabancinuiv.cs310_project_demo.Cs310ProjectDemoApplication;
import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import com.sabancinuiv.cs310_project_demo.service.TodoEntryService;
import com.sabancinuiv.cs310_project_demo.service.TodoEntryValidator;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoEntryController {

    Logger logger = LoggerFactory.getLogger(TodoEntryController.class);

    // Field Injection
    @Autowired private TodoEntryRepository todoRepo;
    @Autowired private TodoEntryValidator todoValidator;
    @Autowired private TodoEntryService todoService;

    @PostConstruct
    void init() {
        logger.info("(DEBUG)(TodoEntryController.java) TodoEntryController has been initialized!");
    }

    // This will return all entries regardless of user id
    @GetMapping("/entries")
    public List<TodoEntry> getAllEntries() {
        List<TodoEntry> entries = todoRepo.findAll();

        return entries;
    }

    // This will return all entries that has the given user id in them
    @GetMapping("/entries/{userId}")
    public ResponseEntity<List<TodoEntry>> getAllEntriesById(@PathVariable String userId) {

        List<TodoEntry> entries = todoRepo.findByUserId(userId);

        if (entries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
    }


    // This will delete the entry with given entry id
    @DeleteMapping("/entries/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteEntry(@PathVariable String id) {    //R.P:  entries/554
        todoRepo.deleteById(id);
        String response = "Entry with id of " + id + " has been deleted from the database.";
        return response;
    }


    /*
        Frontend'den ne dönecek ?
            -> entry'i yazan user in idsi
            -> entry titlesi
            -> entry contenti
            -> entry categorysi
            -> entry due datesi
    */
    /*
        TODO -> DAHA SONRA DIREKT TodoEntry objesi döndürmek yerine özel bir DTO yaz, onu döndür. arada DTO->TodoEntry ve vice versa mapper yarat.
            çünkü:
                - It decouples the database entity from the API response, allowing the internal model to evolve independently of the API.
                - It prevents exposing internal details of the entity that the front-end may not need or should not know about, enhancing security.
                - It provides the flexibility to shape the data in a way that's most convenient for the client.
                - It reduces the risk of inadvertently exposing data that might be added to the entity in the future.
                - DTOs can be tailored to aggregate data from multiple entities, which is useful for complex data structures.
     */

    @PostMapping("/entries/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveEntry(@RequestBody TodoEntry input) {

        //String validationResult = todoValidator.validate(input); // alttakinden sorun cıkarsa alttakini sil bunu kullan
        String validationResult = todoService.validate(input);
        if(validationResult.equals("PASSED")) {

            //bu mongonun kendi generate ettigi id lerde calısmıyor olabilir(idyi manuel atarken çalışıyordu), Userde oldugu gibi buna da "aynısı varsa ekleme"
            //fonksiyonalitesini kazandırmamız lazım.
            // TODO BURADA ID YI NULL ATA FRONTENDDEN GELEN, SEN SAVE LEDIGINDE ID KENDI ATANACAK ZATEN
            if (todoRepo.existsById(input.getId()) == true){
                // Entry already exists with the given ID
                return new ResponseEntity<>("An entry with the given ID already exists !", HttpStatus.CONFLICT);
            }

            System.out.print("(DEBUG) The entry given by the user does not exist in the DB, proceeding...");

            input.setCreateDate(LocalDateTime.now());
            input.setStatus(true);

            TodoEntry savedEntry = todoRepo.save(input);
            return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("ERROR " + validationResult + " !\n", HttpStatus.BAD_REQUEST);
        }
    }
}




