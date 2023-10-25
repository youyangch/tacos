package com.belence.tacos.repository;

import com.belence.tacos.entity.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder,Long> {
}
