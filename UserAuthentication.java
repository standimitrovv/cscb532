package com.example.logisticcompany.models.entity;

import com.example.logisticcompany.util.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_authentication")
public class UserAuthentication {

    // current user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "user_name")
    private String userName;

    @Column(nullable = false)
    private String password;

    @Enumerated
    private UserType role;

    @OneToOne
    private Employee employee;

    @OneToOne
    private Client client;

    @Override
    public String toString() {
        return "UserAuthentication{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", employee=" + employee +
                ", client=" + client +
                '}';
    }
}
