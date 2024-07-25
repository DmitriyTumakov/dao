package ru.netology.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMERS", schema = "order_schema")
public class Customers {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String age;

    @Column
    private String phone_number;
}
