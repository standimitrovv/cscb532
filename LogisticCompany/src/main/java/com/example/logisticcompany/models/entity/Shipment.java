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

    @OneToOne
    private Client sender;

    @OneToOne
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
    @OneToOne
    private Office office;

    @Column(nullable = false, name = "created_at")
    private LocalDate createdAt;

    @Column
    private BigDecimal deliveryFee;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private BigDecimal priceWithDeliveryFee;

    @Enumerated
    private DeliveryType deliveryType;

    // which employee has processed the shipment
    @OneToOne
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
                ", priceWithDeliveryFee=" + priceWithDeliveryFee +
                ", deliveryType=" + deliveryType +
                ", employee=" + employee +
                '}';
    }
}
