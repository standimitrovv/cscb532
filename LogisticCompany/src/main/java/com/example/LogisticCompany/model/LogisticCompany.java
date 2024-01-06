package com.example.LogisticCompany.model;

import com.example.LogisticCompany.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    @Column(nullable = false, unique = true, length = 45)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @OneToMany(mappedBy = "logisticCompany")
    private Set<Office> offices;

    @OneToMany(mappedBy = "logisticCompany")
    private Set<Income> incomes;

    @OneToMany(mappedBy = "logisticCompany")
    private Set<Employee> employees;
}