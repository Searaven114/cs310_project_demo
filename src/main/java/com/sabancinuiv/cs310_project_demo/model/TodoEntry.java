package com.sabancinuiv.cs310_project_demo.model;

//NOT COMPLETED YET ! ASSUME ITS SIMILAR TO USER.JAVA
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "entries")
public class TodoEntry {

    @Id
    private int id;
    private int user_id;
    private String content;
    private String title;
    private String category; //Enum mu yapsak ?
    private boolean status;
    private LocalDateTime createDate;
    private LocalDateTime dueDate; //Bu opsiyonel olmalı, seçmeyen birinde nasıl görünecek ? null mu yoksa boş field mi olacak ? öğren.
    //private String summary;  //OpenAI apisi ile döndürülen "content" özetini bir değişkende mi saklayacağız ? kullanıcı özet alma seçeneği kullanmadığında ne durumda olacak bu değişken ? etc etc

    public TodoEntry(){}

    public TodoEntry(int id, int user_id, String content, String title, String category, boolean status, LocalDateTime createDate, LocalDateTime dueDate) {
        this.id = id;
        this.user_id = user_id;
        this.content = content;
        this.title = title;
        this.category = category;
        this.status = status;
        this.createDate = createDate;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "TodoEntry{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                ", dueDate=" + dueDate +
                '}';
    }

    //NOT COMPLETED YET ! ASSUME ITS SIMILAR TO USER.JAVA
}
