package com.wch.mall.coupon.dao;

import com.wch.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author wuchaohui
 * @email wchaohui@foxmail.com
 * @date 2020-04-27 19:28:07
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
