package com.example.jpa.domain;

import javax.persistence.*;
import java.util.List;

// 1-1
// 1-N
// M-N

//  - jednokierunkowa (jedna encja wie o istnieniu drugiej, ale druga o pierwszej nie)
//  - dwukierunkowa

@Entity
@Table(name = "tm_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "orderNazwaPolaWKlasieZaleznej", fetch = FetchType.EAGER) // robi dodatkowa tabele, Lazy fetch na OrderItem z defaulta
    List<OrderItem> orderItemList;
}
