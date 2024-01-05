package com.example.logisticcompany.models.entity;

import com.example.logisticcompany.util.enums.DeliveryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Client sender;

    @ManyToOne
    private Client receiver;

    @Column(nullable = false, name = "delivery_address")
    private String deliveryAddress;

    @Column(nullable = false)
    private BigDecimal weight;

    @Column
    private boolean isSent;

    @Column
    private boolean isDelivered;

    // which office is the shipment created from
    @ManyToOne
    private Office office;

    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    @Column
    private BigDecimal deliveryFee;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated
    private DeliveryType deliveryType;

    // which employee has processed the shipment
    @ManyToOne
    private Employee employee;

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", weight=" + weight +
                ", isSent=" + isSent +
                ", isDelivered=" + isDelivered +
                ", office=" + office +
                ", createdAt=" + createdAt +
                ", deliveryFee=" + deliveryFee +
                ", price=" + price +
                ", deliveryType=" + deliveryType +
                ", employee=" + employee +
                '}';
    }
}
