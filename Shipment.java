package com.example.logisticcompany.models.entity;

import com.example.logisticcompany.util.DeliveryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

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

    // set the time of shipment creation to be the current time stamp
    @Column(nullable = false, name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

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
