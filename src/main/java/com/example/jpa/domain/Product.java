package com.example.jpa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "store_product",
            joinColumns = { @JoinColumn(name = "fk_store")},
            inverseJoinColumns = { @JoinColumn(name = "fk_product")}
    )
    Set<Store> stores = new HashSet<>(); // Set bardzo istotny!, Lista powoduje potezny spadek wydajnosci, bo przy DML usuwa wszystko z listy i dodaje ponownie

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    public void addStore(Store store) {
        store.getProducts().add(this);
        stores.add(store);
    }
}
