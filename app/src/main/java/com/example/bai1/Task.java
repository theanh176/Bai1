package com.example.bai1;

public class Task {
    private int IdTask;
    private String Title;
    private String Description;
    public int getIdTask() {
        return IdTask;
    }

    public void setIdTask(int idTask) {
        IdTask = idTask;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Task(int idTask, String title, String description) {
        IdTask = idTask;
        Title = title;
        Description = description;
    }
    public Task() {

    }

}
