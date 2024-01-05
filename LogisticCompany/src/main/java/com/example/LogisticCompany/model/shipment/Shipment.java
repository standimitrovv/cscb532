package com.example.LogisticCompany.model.shipment;

import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.employee.Employee;
import com.example.LogisticCompany.model.Office;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "shipments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "delivery_address")
    private String deliveryAddress;

    @Column(nullable = false)
    private BigDecimal weight;

    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    @Column(nullable = false)
    private BigDecimal deliveryFee;

    @Column(nullable = false, name = "shipment_cost")
    private BigDecimal shipmentCost;

    @Column(name = "shipment_status")
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @Column(nullable = false, name = "delivery_type")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    // which employee has processed the shipment
    @OneToOne
    private Employee employee;

    // which office is the shipment created from
    @OneToOne
    private Office office;

    @OneToOne
    private Client sender;

    @OneToOne
    private Client receiver;
}
