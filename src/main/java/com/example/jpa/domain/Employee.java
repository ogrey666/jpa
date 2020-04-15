package com.example.jpa.domain;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Immutable
public class Employee {

    public static final String TASKS_COUNT = "get_employee_task_count"; // nazwa procedury jako stala

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;
    public String getName() {
        return name;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public static Employee create(String name, Task... tasks) {
        Employee e = new Employee();
        e.name = name;
        if (tasks != null) {
            e.tasks = Arrays.asList(tasks);
        }
        return e;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}