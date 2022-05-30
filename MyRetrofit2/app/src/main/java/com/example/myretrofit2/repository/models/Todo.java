package com.example.myretrofit2.repository.models;

/**
 * {
 * "userId": 1,
 * "id": 1,
 * "title": "delectus aut autem",
 * "completed": false
 * }
 */
public class Todo {
    private int userId;
    private int id;
    private String title;
    private Boolean completed;

    public Todo(int userId, int id, String title, Boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
