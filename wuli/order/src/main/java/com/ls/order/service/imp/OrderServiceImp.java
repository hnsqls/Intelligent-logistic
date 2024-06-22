package com.ls.order.service.imp;

import com.ls.order.dao.OrderMapper;
import com.ls.order.pojo.Order;
import com.ls.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public List<Order> findAllOrder() {
        return orderMapper.selectAll();
    }

    @Override
    public int update(Order order) {
        return orderMapper.updateone(order);
    }

    @Override
    public int deleteone(Integer id) {
        return orderMapper.deleteone(id);
    }

    @Override
    public int add(Order order) {
        return orderMapper.insertOne(order);
    }
}
