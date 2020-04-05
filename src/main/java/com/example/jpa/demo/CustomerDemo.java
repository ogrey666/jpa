package com.example.jpa.demo;

import com.example.jpa.domain.Customer;
import com.example.jpa.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDemo {

    @Bean
    public CommandLineRunner customerDemoBean(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer("Jan", "Pies"));
            customerRepository.save(new Customer("Tomasz", "Lis"));
            customerRepository.save(new Customer("Patryk", "Lis"));
            customerRepository.save(new Customer("Sebastian", "Kot"));
            customerRepository.save(new Customer("Henryk", "Mysz"));
            customerRepository.save(new Customer("Zbigniew", "Niedwiedź"));

            System.out.println("Customers find all");
            System.out.println(customerRepository.findAll());
            System.out.println("find by id");
            System.out.println(customerRepository.findById(2L));
            System.out.println("find by lastname");
            System.out.println(customerRepository.findByLastName("Lis"));
        };
    }
}