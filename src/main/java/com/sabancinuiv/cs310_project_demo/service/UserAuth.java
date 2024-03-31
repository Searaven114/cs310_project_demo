package com.sabancinuiv.cs310_project_demo.service;

import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserAuth {

    @Autowired
    UserRepository userRepo;

    //private Map<String, String> authMap; //ex: "Timblohm@protonmail.com" -> "6123012512"

    // burada user auth logicini kur spring security kullanmayacaksan, secret i degerlendir, muhtemelen
    // yapılan her işlemde bir "mapte bu secret var mı ve entry üzerinde işlem yapan adamın tanımlayıcı değeri (mesela email) ile
    // bu secret birbirine karşılık geliyor mu diye bak ve işlem ön koşulu olarak bunu uygula.
    // kötü yanı ateşi baştan keşfediyorsun, performansı iyi degil ve tam guvenli degil.

    // YADA oturup JWT öğrenecez JWT ile authentication + authorization sağlarız. zaman alır.
    // JWT zaten starter security modulesi ile geliyor sanırım, kötü yanı: zaman alır öğrenimi



}
