package com.suziesta.Order.repository;

import com.suziesta.Order.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,String> {
}
