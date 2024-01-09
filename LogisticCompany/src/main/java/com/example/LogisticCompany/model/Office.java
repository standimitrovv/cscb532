package com.example.LogisticCompany.model;

import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.model.logisticCompany.LogisticCompany;
import com.example.LogisticCompany.model.shipment.Shipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "offices")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @OneToMany(mappedBy = "office")
    private Set<Employee> employees;

    @OneToMany(mappedBy = "office")
    private Set<Shipment> shipments;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id", nullable = false)
    private LogisticCompany logisticCompany;
}
