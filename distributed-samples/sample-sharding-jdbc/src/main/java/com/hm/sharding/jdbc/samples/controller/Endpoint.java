package com.hm.sharding.jdbc.samples.controller;

import com.hm.sharding.jdbc.samples.domain.Orders;
import com.hm.sharding.jdbc.samples.service.OrdersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Endpoint.
 *
 * @author huwenfeng
 */
@RestController
@RequestMapping("/endpoint")
public class Endpoint {

    @Resource
    private DataSource dataSource;

    @Resource
    private OrdersService ordersService;
    
    @GetMapping
    public String login() {
        System.out.println("dataSource = " + dataSource);
        return "dataSource";
    }

    @GetMapping("/add")
    public String add() {
        List<Orders> ordersLst = new ArrayList<>();
        for (int i = 0 ; i < 20 ; i++) {
            Orders orders = new Orders();
            orders.setUserId(i + 1);
            ordersLst.add(orders);
        }
        ordersService.saveBatch(ordersLst);
        return "success";
    }
    
    @GetMapping("/list")
    public Object list() {
        List<Orders> list = ordersService.list();
        System.out.println("list = " + list);
        return "success";
    }
    
}
