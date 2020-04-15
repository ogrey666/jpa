package com.example.jpa.demo;

import com.example.jpa.domain.Employee;
import com.example.jpa.domain.Task;
import com.example.jpa.repository.EmployeeRepository;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

@Configuration
public class FetchDemo {

    @Bean
    public CommandLineRunner employeeRunner(EmployeeRepository employeeRepository, EntityManager entityManager, TransactionTemplate transactionTemplate) {
        return args -> {
            Task task1 = new Task("Coding", "Denise");
            Task task2 = new Task("Refactoring", "Rose");
            Task task3 = new Task("Designing", "Denise");
            Task task4 = new Task("Documentation", "Mike");
            Employee employee1 = Employee.create("Diana", task1, task3);
            Employee employee2 = Employee.create("Mike", task2, task4);
            Employee employee3 = Employee.create("Tim", task3, task4);
            Employee employee4 = Employee.create("Jack");

            employeeRepository.saveAll(Arrays.asList(employee1, employee2, employee3, employee4));

            // bez transakcji wywali blad przy pobieraniu zaleznej klasy taskow
            // failed to lazily initialize a collection of role: com.example.jpa.domain.Employee.tasks, could not initialize proxy - no Session

            transactionTemplate.execute(transactionStatus -> {
                Query query = entityManager.createQuery("SELECT DISTINCT e FROM Employee e");
                List<Employee> resultList = query.getResultList();
                for (Employee employee : resultList) {
                    System.out.println(employee.getName() + " - " + employee.getTasks());
                }
                return null;
            });

// dociaganie projekcji, ale lepiej to robic w @Repository jako @Procedure
//            Query query = entityManager.createQuery("SELECT DISTINCT new com.example.jpa.demo.EmpProjection(e.name) FROM Employee e", EmpProjection.class);

            // Wywolanie procedury!
            employeeRepository.getTasksCount("Diana");

            // fetch left join
//            Query query = entityManager.createQuery("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.tasks t");
//            List<Employee> resultList = query.getResultList(); // lub generalizujac: List<Object[]> - TABLICA!
//            for (Employee employee : resultList) {
//                System.out.println(employee.getName() + " - " + employee.getTasks());
//            }
        };
    }

}

class EmpProjection {
    String name;

    public EmpProjection(String name) {
        this.name = name;
    }
}