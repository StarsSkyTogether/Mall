package com.wch.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.wch.common.valid.AddGroup;
import com.wch.common.valid.UpdateField;
import com.wch.common.valid.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author wuchaohui
 * @email wchaohui@foxmail.com
 * @date 2020-04-27 16:08:00
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@NotNull(message = "修改id不能为空",groups = {UpdateGroup.class, UpdateField.class})
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */

	@NotEmpty(message = "新增logo地址不能为空",groups = {AddGroup.class})
	@URL(message = "logo地址必须是个合法的URL地址",groups = {AddGroup.class,UpdateGroup.class})
	private String logo;
	/**
	 * 介绍
	 */

	@NotEmpty(message="备注不能为空")
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@NotNull(message = "新增id不能为空",groups = {AddGroup.class,})
	@Min(value=0,message = "显示状态必须是0或1",groups = {AddGroup.class,UpdateGroup.class, UpdateField.class})
	@Max(value=1,message = "显示状态必须是0或1",groups = {AddGroup.class,UpdateGroup.class, UpdateField.class})
	//@NotEmpty(message = "显示状态不能为空")
	//@Pattern(regexp = "//^[0-1]$//",message = "显示状态必须是0或者1")
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotEmpty(message = "新增检索首字母不能为空",groups = {AddGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首英文必须是英文字母",groups = {AddGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */

	@NotNull(message = "新增排序字段不能为空",groups = {AddGroup.class})
	@Min(value = 0,message = "排序字段必须大于等于0",groups = {AddGroup.class,UpdateGroup.class})
	private Integer sort;

}
