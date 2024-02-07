package com.example.LogisticCompany.model.employee;

import com.example.LogisticCompany.model.logisticCompany.LogisticCompany;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.model.Person;
import com.example.LogisticCompany.model.shipment.Shipment;
import com.example.LogisticCompany.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "registeredByEmployee")
    private Set<Shipment> registeredShipments = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id", nullable = false)
    private LogisticCompany logisticCompany;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="office_id", referencedColumnName = "id", nullable = false)
    private Office office;
}
