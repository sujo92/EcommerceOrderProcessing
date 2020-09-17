package com.suziesta.Order.repository;

import com.suziesta.Order.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.id = :id")
    public List<Order> findAllbyId(@Param("id") String id);

    @Modifying(clearAutomatically = true)
    @Query("update Order o set o.status =:status, o.modifiedDate=:modifiedDate where o.orderId =:orderId")
    public void updateStatus(@Param("orderId") String id, @Param("modifiedDate") Timestamp modifiedDate, @Param("status") String status);

}
