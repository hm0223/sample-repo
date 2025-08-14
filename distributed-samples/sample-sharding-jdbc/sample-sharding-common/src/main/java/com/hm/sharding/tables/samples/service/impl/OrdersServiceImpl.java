package com.hm.sharding.tables.samples.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hm.sharding.tables.samples.domain.Orders;
import com.hm.sharding.tables.samples.mapper.OrdersMapper;
import com.hm.sharding.tables.samples.service.OrdersService;
import org.springframework.stereotype.Service;

/**
 * @apiNote 针对表【orders(订单表)】的数据库操作Service实现
 * 
 * @author huwenfeng
 * @since 2023-06-19 15:47:21
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService {

}