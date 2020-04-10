package com.example.jpa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "stores")
    private Set<Product> products = new HashSet<>();


    public Set<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        product.getStores().add(this);
        products.add(product);
    }
}
