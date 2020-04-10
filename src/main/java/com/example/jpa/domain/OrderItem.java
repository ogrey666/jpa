package com.example.jpa.domain;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;
    // jeden Order ma w sobi wiele itemow
    @ManyToOne(fetch = FetchType.LAZY) //order-item.order_id
    @JoinColumn(name = "super_order_id")
    private Order orderNazwaPolaWKlasieZaleznej;

    public Order getOrder() {
        return orderNazwaPolaWKlasieZaleznej;
    }

    public void setOrder(Order order) {
        this.orderNazwaPolaWKlasieZaleznej = orderNazwaPolaWKlasieZaleznej;
    }
}
