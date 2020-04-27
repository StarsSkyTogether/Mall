package com.wch.mall.product.dao;

import com.wch.mall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author wuchaohui
 * @email wchaohui@foxmail.com
 * @date 2020-04-27 16:08:00
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
