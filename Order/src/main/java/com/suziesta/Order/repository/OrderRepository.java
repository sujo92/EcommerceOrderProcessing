package com.suziesta.Order.repository;

import com.suziesta.Order.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("SELECT o FROM Order o WHERE o.id = :id")
    public List<Order> findAllbyId(@Param("id") String id);

    @Modifying
    @Query("update Order o set o.status = :status where o.id = :id")
    public void updateStatus(@Param("status") String status, @Param("id") String id);

}
