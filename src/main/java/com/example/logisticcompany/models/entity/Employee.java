package com.example.logisticcompany.models.entity;

import com.example.logisticcompany.util.enums.EmployeeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends User {

    @Enumerated
    @Column(nullable = false)
    private EmployeeType role;

    @Column(nullable = false)
    private boolean isAdmin;

    @ManyToOne
    private Office office;

    @Override
    public String toString() {
        return "Employee{" +
                "role=" + role +
                ", office=" + office +
                '}';
    }
}
