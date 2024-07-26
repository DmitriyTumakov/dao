package ru.netology.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS", schema = "order_schema")
public class Orders {
    @Id
    @GeneratedValue
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @CurrentTimestamp
    private String date;

    @OneToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customers customers;

    @Column(name = "product_name")
    private String productName;

    @Column
    private int amount;
}
