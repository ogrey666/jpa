package com.example.jpa.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue()
    private Long id;
    private String name;
    private Integer age;
    private Integer status;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime contactDate;

    protected User() {
    }

    ;

    public User(String name, Integer age, Integer status, String email, LocalDateTime contactDate) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.email = email;
        this.contactDate = contactDate;
    }

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", createDate=" + createDate +
                ", contactDate=" + contactDate +
                '}';
    }
}