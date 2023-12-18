package com.example.logisticcompany.models.dto;

import com.example.logisticcompany.exceptions.InvalidNegativeOrZeroValue;
import com.example.logisticcompany.exceptions.InvalidType;
import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Employee;
import com.example.logisticcompany.models.entity.Office;
import com.example.logisticcompany.util.Validation;
import com.example.logisticcompany.util.enums.DeliveryType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class ShipmentDto {

    private Client sender;
    private Client receiver;
    private String deliveryAddress;
    private BigDecimal weight;
    private boolean isSent;
    private boolean isDelivered;
    private Office office;
    private LocalDate createdAt;
    private BigDecimal deliveryFee;
    private BigDecimal price;
    private BigDecimal priceWithDeliveryFee;
    private DeliveryType deliveryType;
    private Employee employee;

    public ShipmentDto(Client sender, Client receiver, String deliveryAddress, BigDecimal weight, Office office, BigDecimal price, DeliveryType deliveryType, Employee employee) throws InvalidNegativeOrZeroValue, InvalidType {
        this.setSender(sender);
        this.setReceiver(receiver);
        this.setDeliveryAddress(deliveryAddress);
        this.setWeight(weight);
        this.isSent = true; // by default if a shipment is created it is sent
        this.isDelivered = false; // by default a shipment is set to not delivered when created
        this.setOffice(office);
        this.createdAt = LocalDate.now();
        this.setDeliveryType(deliveryType);
        this.setDeliveryFee();
        this.setPrice(price);
        this.setPriceWithDeliveryFee();
        this.setEmployee(employee);
    }

    private void setSender(Client sender) {
        if (sender == null) {
            // cannot be left empty
            // TODO: more user friendly message
            throw new NullPointerException("The sender should be a valid instance!");
        }
        this.sender = sender;
    }

    private void setReceiver(Client receiver) {
        if (receiver == null) {
            // cannot be left empty
            // TODO: more user friendly message
            throw new NullPointerException("The receiver should be a valid instance!");
        }
        this.receiver = receiver;
    }

    private void setDeliveryAddress(String deliveryAddress) {
        if (Validation.checkIfNullOrEmpty(deliveryAddress)) {
            throw new NullPointerException("Delivery address should be a valid string!");
        }
        this.deliveryAddress = deliveryAddress;
    }

    private void setWeight(BigDecimal weight) throws InvalidNegativeOrZeroValue {
        if (Validation.validatePositiveValue(weight)) {
            this.weight = weight;
        }
    }

    private void setOffice(Office office) {
        if (office == null) {
            // cannot be left empty
            // TODO: more user friendly message
            throw new NullPointerException("The office should be a valid instance!");
        }
        this.office = office;
    }

    private void setDeliveryFee() {
        if (this.deliveryType.equals(DeliveryType.OFFICE)) {
            this.deliveryFee = BigDecimal.valueOf(5);
        } else if (this.deliveryType.equals(DeliveryType.ADDRESS)) {
            this.deliveryFee = BigDecimal.valueOf(7.50);
        }
    }

    private void setPrice(BigDecimal price) throws InvalidNegativeOrZeroValue {
        if (Validation.validatePositiveValue(price)) {
            this.price = price;
        }
    }

    private void setPriceWithDeliveryFee() {
        this.priceWithDeliveryFee = this.price.add(this.deliveryFee);
    }

    private void setDeliveryType(DeliveryType deliveryType) throws InvalidType {
        if (!deliveryType.equals(DeliveryType.OFFICE) && !deliveryType.equals(DeliveryType.ADDRESS)) {
            throw new InvalidType("The selected delivery type is invalid!");
        }

        this.deliveryType = deliveryType;
    }

    private void setEmployee(Employee employee) {
        if (employee == null) {
            // cannot be left empty
            throw new NullPointerException("The employee field should be a valid instance!");
        }
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "ShipmentDto{" +
                "sender=" + sender +
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
