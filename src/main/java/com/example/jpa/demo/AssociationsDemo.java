package com.example.jpa.demo;

import com.example.jpa.domain.Order;
import com.example.jpa.domain.OrderItem;
import com.example.jpa.domain.Product;
import com.example.jpa.domain.Store;
import com.example.jpa.repository.OrderItemRepository;
import com.example.jpa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;

@Configuration
public class AssociationsDemo {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Bean
    public CommandLineRunner associationDemoBean(OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        return args -> {
//            Order order = new Order();
//            Order savedOrder = orderRepository.save(order);
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItemRepository.save(orderItem);
//            Iterable<OrderItem> all = orderItemRepository.findAll();
//            System.out.println(all);
//            orderRepository.findById(1L);
            transactionTemplate.execute(transactionStatus -> {
                Store store = new Store();
                entityManager.persist(store);
                Product product = new Product();
                product.addStore(store);
//                product.setStores(Collections.singleton(store));
                entityManager.persist(product);
                System.out.println(product);
                entityManager.find(Product.class, 1L); // niskopoziomowa metoda do wyszukiwania encji po ID
                return product;
            });


        };
    }
}
