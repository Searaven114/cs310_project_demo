package com.sabancinuiv.cs310_project_demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "entries")
public class TodoEntry {

    @Id
    private String id;
    private String userId;
    private String content;
    private String title;
    private String category;
    private boolean status;
    private LocalDateTime createDate;
    private LocalDateTime dueDate;
    //private String summary;      // OpenAI API'si bağladığımızda değerlendireceğiz.

    public TodoEntry(){}

    public TodoEntry(String id, String userId, String content, String title, String category, boolean status, LocalDateTime createDate, LocalDateTime dueDate) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.title = title;
        this.category = category;
        this.status = status;
        this.createDate = createDate;
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                ", dueDate=" + dueDate +
                '}';
    }

}
