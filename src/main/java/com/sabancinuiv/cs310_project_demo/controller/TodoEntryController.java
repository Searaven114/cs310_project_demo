package com.sabancinuiv.cs310_project_demo.controller;

import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import com.sabancinuiv.cs310_project_demo.dto.TodoEntryDTO;
import com.sabancinuiv.cs310_project_demo.model.User;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import com.sabancinuiv.cs310_project_demo.dto.TodoEntryDTO_2;
import com.sabancinuiv.cs310_project_demo.service.TodoEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


//TODO
// -> Display GET endpointlerine pagination ekle.

@RestController
@RequestMapping("/api/v1")
public class TodoEntryController {

    @Autowired
    private TodoEntryRepository todoRepo;

    //Due to the overhead, we did not utilize TodoEntryService for this controller, might do it later.
    @Autowired
    private TodoEntryService todoService;

    @Autowired
    private UserRepository userRepo;

    /**
     * A method will 2 query parameters that fetches the entries of the user.
     * @param status query parameter for indicating the status of the entries to be fetched ("checked" / "unchecked")
     * @param dueStatus query parameter for indicating the dueDate's of the entries to be fetched ("past" / "future")
     * @return a ResponseEntity that either carries the result state of the process as a String, or array of JSON.
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/entries")
    public ResponseEntity<?> displayEntries(@RequestParam(required = false) String status, @RequestParam(required = false) String dueStatus) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>("UNAUTHORIZED REQUEST", HttpStatus.UNAUTHORIZED);
        }

        List<TodoEntry> entries;

        if ("checked".equals(status)) {
            entries = todoRepo.findByUserIdAndStatus(user.getUserId(), false);
        } else if ("unchecked".equals(status)) {
            entries = todoRepo.findByUserIdAndStatus(user.getUserId(), true);
        } else {
            entries = todoRepo.findByUserId(user.getUserId());
        }

        //Filtering logic
        if (dueStatus != null) {

            LocalDateTime now = LocalDateTime.now();

            //Handling the dueDate query parameter & sorting based of dueDate variable
            if ("past".equals(dueStatus)) {
                entries = entries.stream()
                        .filter(e -> e.getDueDate() != null && e.getDueDate().isBefore(now))
                        .sorted(Comparator.comparing(TodoEntry::getDueDate))
                        .collect(Collectors.toList());
            } else if ("future".equals(dueStatus)) {
                entries = entries.stream()
                        .filter(e -> e.getDueDate() != null && e.getDueDate().isAfter(now))
                        .sorted(Comparator.comparing(TodoEntry::getDueDate))
                        .collect(Collectors.toList());
            } else {
                entries = entries.stream()
                        .sorted(Comparator.comparing(TodoEntry::getDueDate))
                        .collect(Collectors.toList());

            }
        }

        return ResponseEntity.ok().body(entries);
    }

    /**
     * Displays the entries of the user, given a certain category.
     * @param category The category that the user wants the entries to be fetched with. e.g "BLUE" category
     * @return a ResponseEntity that either carries the result state of the process as a String, or array of JSON.
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/entries/category/{category}")
    public ResponseEntity<?> displayEntriesByCategory(@PathVariable String category) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>("UNAUTHORIZED REQUEST", HttpStatus.UNAUTHORIZED);
        }

        // Get entries by category for the user
        List<TodoEntry> entries = todoRepo.findByUserIdAndCategory(user.getUserId(), category);

        return ResponseEntity.ok().body(entries);
    }
    /*
    * TODO
    *  İleride Bunu ayrı bir endpoint değil de, asıl GET handler'e bir Query Parametresi olarak eklesek daha doğru olur gibi.
    * */


    /**
     * A method that will be used for user registeration, is permitted to all inbound http requests by default.
     * @param dto the object that will get its fields mapped into a TodoEntry object for saving to the db
     * @return a ResponseEntity that either carries the result state of the process as a String, Exception is incorporated into this string.
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "/entries", consumes = "application/json")
    public ResponseEntity<?> saveEntry(@RequestBody TodoEntryDTO dto) {

        //Association logic
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);

        if (user == null){
            return new ResponseEntity<>("UNAUTHORIZED REQUEST",HttpStatus.UNAUTHORIZED);
        } else {

            String result = TodoEntryService.validate(dto);

            if (result.equals("PASSED")) {

                TodoEntry newEntry = new TodoEntry();

                //Mapping logic
                newEntry.setUserId(user.getUserId());
                newEntry.setTitle(dto.getTitle());
                newEntry.setContent(dto.getContent());
                newEntry.setCategory(dto.getCategory());
                newEntry.setStatus(dto.getStatus()); // True -> unchecked, False -> checked
                newEntry.setCreateDate(LocalDateTime.now());
                newEntry.setDueDate(LocalDateTime.now()); //Normalde DTO'nun user'den bir dueDate döndürmesi lazım, ben timestamp attım.

                todoRepo.save(newEntry);

                return new ResponseEntity<>("NEW ENTRY HAS BEEN CREATED", HttpStatus.CREATED);

            } else {

                return ResponseEntity.badRequest().body("ERROR " + result);
            }
        }
    }


    /**
     * A method that will be used for updating existing entries of user.
     * @param id the id of the entry to be updated.
     * @param dto the fields that will replace old entries already existing fields.
     * @return a ResponseEntity that either carries the result state of the process as a String, might also return standalone HTTP statuscode.
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping(value = "/entries/{id}",consumes = "application/json")
    public ResponseEntity<?> updateEntry(@PathVariable String id, @RequestBody TodoEntryDTO_2 dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepo.findByUsername(username);

        TodoEntry targetEntry = todoRepo.findByIdAndUserId(id, user.getUserId());

        if (targetEntry == null) {
            return ResponseEntity.notFound().build();
        }

        targetEntry.setTitle(dto.getTitle());
        targetEntry.setContent(dto.getContent());
        targetEntry.setCategory(dto.getCategory());
        targetEntry.setDueDate(dto.getDueDate());

        todoRepo.save(targetEntry);
        return ResponseEntity.ok().body("Entry has been updated");
    }


    /**
     * A method that will be used to implement "check / uncheck" mechanism of frontend, this will toggle the "status" variable of "User" objects.
     * @param id id of the entry to be toggled.
     * @return a ResponseEntity that either carries the result state of the process as a String, might also return standalone HTTP statuscode.
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PatchMapping("/entries/{id}/toggle-status")
    public ResponseEntity<?> toggleStatus(@PathVariable String id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepo.findByUsername(name);

        TodoEntry targetEntry = todoRepo.findByIdAndUserId(id, user.getUserId());

        if (targetEntry == null) {
            return ResponseEntity.notFound().build();
        }

        // Toggle the status
        targetEntry.setStatus(!targetEntry.isStatus());
        todoRepo.save(targetEntry);

        return ResponseEntity.ok().body("ENTRY STATUS HAS BEEN UPDATED");

    }

    /**
     * a method that will delete a certain entry, given by the user.
     * @param id id of the entry to be deleted
     * @return a String representing the result state of the process.
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/entries/{id}")
    public String deleteEntry(@PathVariable String id) {
        todoRepo.deleteById(id);
        String response = "Entry with id of " + id + " has been deleted from the database.";
        return response;
    }
    //TODO
    //  Error handling yetersiz.

    /**
     * a method for deleting all the entries of the user.
     * @return a ResponseEntity that either carries the result state of the process as a String.
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("/entries")
    public ResponseEntity<?> deleteAllEntries(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepo.findByUsername( name );

        if (user != null){
            todoRepo.deleteAllByUserId( user.getUserId() );
            return ResponseEntity.ok().body("ENTRIES HAS BEEN DELETED");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
        }

        // Boş olsa da çalışır mı yoksa "zaten boş mu değil mi" checki yapmak lazım mı bakmak gerekir..
    }

    /**
     * an admin method that will reset the entire TodoEntry database.
     * @return a ResponseEntity representing the result state of the process.
     */
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/entries/admin/delete-all-entries")
    public ResponseEntity<?> deleteEntryDb(){

        todoRepo.deleteAll();

        return ResponseEntity.ok().body("ENTRY DATABASE HAS BEEN CLEARED");
    }

    /**
     * an admin method that will display the entire TodoEntry database.
     * @return a ResponseEntity representing the result state of the process, comes entries in the database in array of JSON.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/entries/admin/show-all-entries")
    public ResponseEntity<?> showAllEntries(){

        List<TodoEntry> entries = todoRepo.findAll();

        return ResponseEntity.ok().body(entries);
    }
    //TODO -> "Delete Selected" için bir delete endpointi ekleyeceğiz, yada frontendde select ettiği entrylerin
    // id leri bir JSON da tutulacak en son o JSON'dan gelen id'lere teker teker "deleteEntry" uygulanacak.
    // Buna özel bir endpointin olması daha mantıklı geliyor bilemedim. e.g "/api/v1/entries/selected"


}