package com.ls.order.dao;

import com.ls.order.pojo.Order;

import java.util.List;

public interface OrderMapper {
    /**
     * 查询所有订单信
     */
    List<Order> selectAll();

    int updateone(Order order);
    int deleteone(Integer id);

    int insertOne(Order order);
}
