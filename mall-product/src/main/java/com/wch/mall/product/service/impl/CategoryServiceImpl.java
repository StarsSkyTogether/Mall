package com.wch.mall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wch.common.utils.PageUtils;
import com.wch.common.utils.Query;

import com.wch.mall.product.dao.CategoryDao;
import com.wch.mall.product.entity.CategoryEntity;
import com.wch.mall.product.service.CategoryService;

import javax.annotation.Resource;
import javax.annotation.Resources;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

//    @Resource
//    private CategoryDao categoryDao;

    @Override
    public List<CategoryEntity> listWithTree() {
        //1、查出所有分类
        List<CategoryEntity> list = baseMapper.selectList(null);

        //2、组装成父子的树形结构

        //2.1)、找到所有的一级分类
        List<CategoryEntity> level1Menus = list.stream().filter((categoryEntity)->categoryEntity.getParentCid()==0)     //1.过滤出所有的一级分类
                    .map((menu)->{  //2.设置子菜单
                        menu.setChildren(getChildrens(menu,list));
                        return menu;
                    }).sorted((menu1,menu2)->{
                        return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
                    }).collect(Collectors.toList());




        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 1、检查当前删除的菜单，是否被别的地方引用

        //逻辑删除
        baseMapper.deleteBatchIds(asList);
    }

    //递归查找所有的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all){
        List<CategoryEntity> children = all.stream()
                .filter((categoryEntity) -> categoryEntity.getParentCid()==root.getCatId())
                .map(categoryEntity -> {
                    //1、找到子菜单
                    categoryEntity.setChildren(getChildrens(categoryEntity,all));
                    return categoryEntity;
                }).sorted((menu1,menu2) ->{
                    //2、菜单排序
                    return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
                }).collect(Collectors.toList());

        return children;

    }

}