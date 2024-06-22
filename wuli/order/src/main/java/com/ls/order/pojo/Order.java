package com.ls.order.pojo;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private String person;
    private String tel;
    private String addr;
    private String product;

    boolean status ;
}
