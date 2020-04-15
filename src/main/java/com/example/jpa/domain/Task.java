package com.example.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue
    private long id;
    private String description;
    private String supervisor;
    public Task() {
    }
    public Task(String description, String supervisor) {
        this.description = description;
        this.supervisor = supervisor;
    }
    public String getDescription() {
        return description;
    }
    public String getSupervisor() {
        return supervisor;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", supervisor='" + supervisor + '\'' +
                '}';
    }
}