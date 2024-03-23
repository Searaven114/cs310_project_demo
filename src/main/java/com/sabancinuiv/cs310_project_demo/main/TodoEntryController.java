package com.sabancinuiv.cs310_project_demo.main;

import com.sabancinuiv.cs310_project_demo.main.TodoEntry;
import com.sabancinuiv.cs310_project_demo.main.TodoEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/entries") <---- BASE URI BEYANI, ama kullanmadım
public class TodoEntryController {

    @Autowired
    private TodoEntryRepository todoRepo;


    //user login olduktan sonra, adına (id key) yapılan entryleri döndürür kullanıcıya, 200 döndürür, şu an sadece bu endpoint aktif
    //For "ResponseEntity" ---> https://www.youtube.com/watch?v=s39JyC4RIvc
    @GetMapping("/entries")
    public ResponseEntity<?> getAllEntries() {
        List<TodoEntry> entries = todoRepo.findAll();
        return ResponseEntity.ok(entries);
    }

// BUNU YAPMAK ICIN OPTIONAL'I VE FUNCTIONAL INTERFACELERI ANLAMAK LAZIM !
//    @GetMapping("/entries/{id}")     //kullanıcının aradığı spesifik entry'i döndürür, search sonrası çıkan sonuçları sanırım bunu kullanarak dondurecez, 200 döndürür
//    public ResponseEntity<?> getEntryById(@PathVariable String id){
//
//    }


    //ID'si otomatik handle edilecek DBde "id" fieldi auto incrementi ile.. 201 döndürür
    // @PostMapping("/entries")



    //post modification için, ileri versiyonlarda implement ederiz, 200 döndürür?
    // @PutMapping("/entries/{id}")


    //kullanıcının seçtigi entry silinir. 204 dondurur
    // @DeleteMapping("/entries/{id}")



}





/* -Lore-
* ResponseEntity<?> is a way to indicate that the method can return a ResponseEntity object that can contain any type of body.
*  This is particularly useful in APIs where the response body's type may vary depending on the outcome of the request.
* For example, a method might return a list of items, a single item, or an error message, each of which could be of different types.
*/