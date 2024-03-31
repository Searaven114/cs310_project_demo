# *Todo Application Project*

## Son değişimler

~~Paket problemini çözdüm, artık package by layer işliyor~~ Hala bozuk ag <br>
<div>
-> repository objelerini DAO olarak yeniden isimlendirdim<br>
-> TodoEntryController baya ilerledi
-> UserController register kısmı bitti, hash için algoritma eklendi.
-> Debug verisi ve life cycle takibi için @PostConstruct ve @PreDestroy kullandım.
</div>

# Notlar

<div>
<li> Şimdilik mongoDB yi dockersiz kullanalım sonrasında geçeriz dockere.</li><br>
<li> User ve entry arasında sahibiyet bağlantısı kurulması kaldı, ya manuel ya JWT yapacaz <small>(önemli)</small> kaldı</li><br>
<li> xx</li>
</div>

# TODO
<div>
<li> https://beanvalidation.org/ Kullanacağız API girdi kontrolü için, DTO'lara uygulanıyor</li>
<li> UserNotFound ve EntryNotFound exceptionu yazalım bulunmaması bulunmaması durumunda. şart degil ama sanırım </li>
<li>  </li>


</div>

## Know-how
[Response Entity 1](https://www.youtube.com/watch?v=s39JyC4RIvc) <br>
[IOC - DI 1](https://www.simplilearn.com/tutorials/spring-tutorial/spring-ioc-container#:~:text=The%20IoC%20container%20constructs%20an,create%20and%20manage%20objects%20manually.) <br>
[UUID kullanımı](https://github.com/dsyer/spring-todo-mvc/blob/main/src/main/java/example/todomvc/Todo.java)
