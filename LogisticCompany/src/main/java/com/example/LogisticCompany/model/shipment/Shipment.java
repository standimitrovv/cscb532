package com.example.LogisticCompany.model.shipment;

import com.example.LogisticCompany.model.Client;
import com.example.LogisticCompany.model.Office;
import com.example.LogisticCompany.model.employee.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
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
    private int id;

    @Column(name = "delivery_address", nullable = false, length = 50)
    private String deliveryAddress;

    @Column(nullable = false)
    @Digits(integer = 2, fraction = 1)
    private BigDecimal weight;

    @Column(name = "created_at", nullable = false)
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate createdAt;

    @Column(name = "delivery_fee", nullable = false)
    @Digits(integer = 2, fraction = 2)
    private BigDecimal deliveryFee;

    @Column(name = "shipment_cost", nullable = false)
    @Digits(integer = 3, fraction = 2)
    private BigDecimal shipmentCost;

    @Column(name = "shipment_status", columnDefinition = "ENUM('IN_PROCESS', 'SENT', 'IN_TRANSIT', 'COMPLETED') default 'IN_PROCESS'")
    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus = ShipmentStatus.IN_PROCESS;

    @Column(name = "delivery_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name="office_id", referencedColumnName = "id")
    private Office office;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name="sender_id", referencedColumnName = "id", nullable = false)
    private Client sender;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name="receiver_id", referencedColumnName = "id", nullable = false)
    private Client receiver;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name="registered_by_employee_id", referencedColumnName = "id", nullable = false)
    private Employee registeredByEmployee;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "last_updated_by_employee_id", referencedColumnName = "id", nullable = false)
    private Employee lastUpdatedByEmployee;
}
