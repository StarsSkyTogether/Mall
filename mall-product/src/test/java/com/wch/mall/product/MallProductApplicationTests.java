package com.wch.mall.product;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wch.mall.product.dao.CategoryDao;
import com.wch.mall.product.entity.BrandEntity;
import com.wch.mall.product.entity.CategoryEntity;
import com.wch.mall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class MallProductApplicationTests {
    @Autowired
    BrandService brandService;
    @Test
    void contextLoads() {

        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("华为");
        brandService.save(brandEntity);
    }

    @Autowired
    CategoryDao categoryDao;
    @Test
    public void listWithTree() {
        //1、查出所有分类
        List<CategoryEntity> list = categoryDao.selectList(null);

        list.forEach(System.out::println);
    }

    @Test
    public void listAll(){
        List<BrandEntity> brandEntities = brandService.queryAll();
        System.out.println(brandEntities);
        System.out.println(brandEntities.size());
    }

    @Test
    public void batchBrand(){
        for(int i=0; i<5000; i++){
            BrandEntity brandEntity = new BrandEntity();
            brandEntity.setDescript("华为"+i);
            brandEntity.setFirstLetter("a");
            brandEntity.setLogo("a3dd58e7431b_28f296629cca865e.jpg+1");
            brandEntity.setShowStatus(1);
            brandEntity.setSort(1);
            brandEntity.setName("华为"+i);
            brandService.save(brandEntity);
        }

    }

}
