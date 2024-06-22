package com.ls.order.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ls.order.pojo.Order;
import com.ls.order.service.OrderService;
import com.ls.wuliu.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class OrderController {

    @Autowired
    OrderService orderService;
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
       return "hhhh";
    }
    /**
     * 查询所有订单信息
     */
    @ResponseBody
    @GetMapping("/order")
    public R findall(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> allOrder = orderService.findAllOrder();
        PageInfo<Order> orderPageInfo = new PageInfo<>(allOrder);
        return R.success().setExtra(orderPageInfo);
    }

    /**
     * 更新订单信息 PUT
     */
    @ResponseBody
    @PutMapping("/order")
    public R updata(@RequestBody Order order){
        orderService.update(order);
        return R.success();

    }
    /**
     * 删除订单信息 path 路径传参id
     */
    @ResponseBody
    @DeleteMapping("/order/{id}")
    public R remove(@PathVariable("id")Integer id){
        orderService.deleteone(id);
        return R.success();
    }

    /**
     * 新增(保存)订单信息
     */
    @ResponseBody
    @PostMapping("/order")
    public R add(@RequestBody Order order){
        orderService.add(order);
        return R.success();
    }
}
