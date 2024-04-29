package com.sabancinuiv.cs310_project_demo.dto;

import java.time.LocalDateTime;

public class TodoEntryDTO_2 {

    private String title;
    private String content;
    private String category;
    private LocalDateTime dueDate;

    public TodoEntryDTO_2(String title, String content, String category, LocalDateTime dueDate) {
        this.title = title;
        this.content = content;
        this.category = category;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "TodoEntryDTO_2{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}


