package com.example.LogisticCompany.model;

import com.example.LogisticCompany.model.common.Months;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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

    @Column(name = "for_month", nullable = false)
    @Enumerated(EnumType.STRING)
    private Months forMonth;

    @Digits(integer = 10, fraction = 2)
    @Column(nullable = false)
    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", referencedColumnName = "id", nullable = false)
    private LogisticCompany logisticCompany;
}
