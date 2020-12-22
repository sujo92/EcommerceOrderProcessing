package com.suziesta.BulkOrder.repository;


import com.suziesta.BulkOrder.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

}
