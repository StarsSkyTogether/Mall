package com.wch.mall.member.dao;

import com.wch.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author wuchaohui
 * @email wchaohui@foxmail.com
 * @date 2020-04-27 19:40:06
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
