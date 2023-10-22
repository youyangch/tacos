package com.belence.tacos.repository;

import com.belence.tacos.entity.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
