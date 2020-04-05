package com.example.jpa.domain;

import javax.persistence.*;

/*
    Database entity: Customer
 */
@Entity
@Table(schema = "public", name = "cust")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String firstName; // default column name = first_name, overwritten with annotation @Column
    private String lastName;

    // tylko Hibernate może korzystać z tego konstruktora, nikt inny ma nie dodawać klienta bez firstName/lastName
    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
