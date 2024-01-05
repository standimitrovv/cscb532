package com.example.logisticcompany.repository;

import com.example.logisticcompany.models.entity.Client;
import com.example.logisticcompany.models.entity.Shipment;
import com.example.logisticcompany.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Override
    Optional<Shipment> findById(Long id);
    @Override
    List<Shipment> findAll();
    List<Shipment> findAllByDeliveredIsFalse();
    List<Shipment> findAllByEmployeeId(long id);
    List<Shipment> findAllByReceiver(User user);
    List<Shipment> findAllBySender(User user);
    List<Shipment> findAllByReceiverAndDeliveredIsFalse(Client client);
    List<Shipment> findAllByReceiverAndDeliveredIsTrue(Client client);

}
