package com.example.LogisticCompany.model.user;

import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "user_name")
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(name = "user_type", columnDefinition = "ENUM('EMPLOYEE', 'CLIENT') default 'CLIENT'")
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.CLIENT;

    @OneToOne
    private Employee employee;

    @OneToOne
    private Client client;
}
