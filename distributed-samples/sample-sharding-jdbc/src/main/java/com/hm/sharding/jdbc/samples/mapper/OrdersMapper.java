package com.hm.sharding.jdbc.samples.mapper;

import com.hm.sharding.jdbc.samples.domain.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @apiNote 针对表【orders(订单表)】的数据库操作Mapper
 * 
 * @author huwenfeng
 * @since 2023-06-19 15:47:21
 */

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}