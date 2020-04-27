package com.wch.mall.ware.dao;

import com.wch.mall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author wuchaohui
 * @email wchaohui@foxmail.com
 * @date 2020-04-27 19:59:51
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
