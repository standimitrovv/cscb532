package com.example.logisticcompany.models.entity;

import com.example.logisticcompany.util.enums.EmployeeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Enumerated
    private EmployeeType role;

    @ManyToOne
    private Office office;

    @ManyToMany
    private Set<Shipment> allRegisteredPackages;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", office=" + office +
                ", allRegisteredPackages=" + allRegisteredPackages +
                '}';
    }
}
