package com.example.logisticcompany.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "offices")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @OneToMany
    private Set<Employee> employees;

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", employees=" + employees +
                '}';
    }
}
