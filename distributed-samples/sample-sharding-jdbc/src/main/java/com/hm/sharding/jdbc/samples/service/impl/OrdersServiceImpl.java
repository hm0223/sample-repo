package com.hm.sharding.jdbc.samples.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hm.sharding.jdbc.samples.domain.Orders;
import com.hm.sharding.jdbc.samples.service.OrdersService;
import com.hm.sharding.jdbc.samples.mapper.OrdersMapper;
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