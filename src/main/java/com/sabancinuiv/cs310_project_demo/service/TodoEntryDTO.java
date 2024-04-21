package com.sabancinuiv.cs310_project_demo.service;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class TodoEntryDTO {

    private String title;
    private String content;
    private String category;
    private boolean status;
    private LocalDateTime dueDate;


    public TodoEntryDTO(String title, String content, String category, boolean status, LocalDateTime dueDate) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = status;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getStatus(){
        return this.status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "TodoEntryDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", dueDate=" + dueDate +
                '}';
    }
}
