package com.example.LogisticCompany.model;

import com.example.LogisticCompany.model.common.Months;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "incomes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "for_month")
    @Enumerated(EnumType.STRING)
    private Months forMonth;

    @Column(nullable = false)
    private double amount;

    @ManyToOne
    private LogisticCompany logisticCompany;
}
