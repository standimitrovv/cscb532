package com.example.LogisticCompany.model.user;

import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.employee.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique=true, length = 15)
    private String username;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "user_type", columnDefinition = "ENUM('EMPLOYEE', 'CLIENT') default 'CLIENT'")
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.CLIENT;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    @OneToOne(mappedBy = "user")
    private Client client;
}
