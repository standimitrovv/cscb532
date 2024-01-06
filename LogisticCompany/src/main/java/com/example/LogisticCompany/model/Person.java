package com.example.LogisticCompany.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", nullable = false, length = 45)
    private String fullName;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String phoneNumber;
}
