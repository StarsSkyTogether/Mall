package com.wch.mall.order.dao;

import com.wch.mall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author wuchaohui
 * @email wchaohui@foxmail.com
 * @date 2020-04-27 19:53:05
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
