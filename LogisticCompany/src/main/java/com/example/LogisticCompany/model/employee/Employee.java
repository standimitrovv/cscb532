package com.example.LogisticCompany.model.employee;

import com.example.LogisticCompany.model.LogisticCompany;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.model.Person;
import com.example.LogisticCompany.model.shipment.Shipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee extends Person {

    @Column(nullable = false, name = "employee_type")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @ManyToOne
    private Office office;

    @ManyToMany
    private Set<Shipment> allRegisteredPackages;
}
