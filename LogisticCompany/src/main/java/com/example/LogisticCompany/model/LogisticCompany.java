package com.example.LogisticCompany.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "logistic_companies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogisticCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, name = "phone_number", unique = true)
    private String phoneNumber;

//    @OneToMany
//    private Set<Office> offices;
//
//    @OneToMany
//    private Set<Income> incomes;
//
//    @OneToMany
//    private Set<Employee> employees;
}