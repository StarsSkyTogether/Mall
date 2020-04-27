package com.wch.mall.coupon.dao;

import com.wch.mall.coupon.entity.SeckillSessionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动场次
 * 
 * @author wuchaohui
 * @email wchaohui@foxmail.com
 * @date 2020-04-27 19:28:07
 */
@Mapper
public interface SeckillSessionDao extends BaseMapper<SeckillSessionEntity> {
	
}
