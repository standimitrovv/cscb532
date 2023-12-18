package com.example.logisticcompany.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class LogisticCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    // the company has info about:
    // all the offices?
    // all the employees
    // all the registered packages
    // all the registered clients
    // all the registered packages by a particular employee
    // all the sent and not received packages

    @OneToMany
    private Set<Office> offices;

    @OneToMany
    private Set<Employee> employees;

    @OneToMany
    private Set<Shipment> registeredPackages;

    @OneToMany
    private Set<Client> registeredClients;

    // FIXME: doesn't work with Set as a value
    @OneToMany
    private Map<Employee, Set<Shipment>> registeredPackagesByEmployee;

    @OneToMany
    private Set<Shipment> sentAndNotReceivedPackages;

    @Override
    public String toString() {
        return "LogisticCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", offices=" + offices +
                ", employees=" + employees +
                ", registeredPackages=" + registeredPackages +
                ", registeredClients=" + registeredClients +
                ", registeredPackagesByEmployee=" + registeredPackagesByEmployee +
                ", sentAndNotReceivedPackages=" + sentAndNotReceivedPackages +
                '}';
    }
}

