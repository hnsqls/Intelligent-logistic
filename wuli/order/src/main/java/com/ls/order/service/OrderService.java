package com.ls.order.service;

import com.ls.order.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrder();
    int update(Order order);

    int deleteone(Integer id);

    int add(Order order);
}
