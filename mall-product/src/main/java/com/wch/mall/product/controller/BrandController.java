package com.wch.mall.product.controller;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.*;


import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.wch.common.valid.AddGroup;
import com.wch.common.valid.UpdateField;
import com.wch.common.valid.UpdateGroup;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wch.mall.product.entity.BrandEntity;
import com.wch.mall.product.service.BrandService;
import com.wch.common.utils.PageUtils;
import com.wch.common.utils.R;

import javax.validation.Valid;


/**
 * 品牌
 *
 * @author wuchaohui
 * @email wchaohui@foxmail.com
 * @date 2020-04-27 16:08:00
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }

    //导出报表
    @PostMapping("/exportingReport")
    public R exportingReport(@RequestBody  String[] fields){
        List<String> fieldNames = new ArrayList<>();
        for(String str : fields){
            switch (str){
                case "1": fieldNames.add("brandId");break;
                case "2": fieldNames.add("name");break;
                case "3": fieldNames.add("logo");break;
                case "4": fieldNames.add("descript");break;
                case "5": fieldNames.add("showStatus");break;
                case "6": fieldNames.add("firstLetter");break;
                case "7": fieldNames.add("sort");break;
                default: break;
            }
        }
        System.out.println(fieldNames.size());
        List<BrandEntity> brandEntities = brandService.queryAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //设置表头
        System.out.println("22222222222222222222");
        Row headRow = sheet.createRow(0);
        for(int c=0; c<fieldNames.size(); c++){
            Cell cell = headRow.createCell(c);
            cell.setCellValue(fieldNames.get(c));
        }


        //设置表的内容
        for(int i=0; i<brandEntities.size(); i++){
            BrandEntity brandEntity = brandEntities.get(i);
            Row row = sheet.createRow(i+1);
            for(int j=0; j<fieldNames.size(); j++){
                Cell cell = row.createCell(j);
                try{
                    //通过反射获取相应属性名的属性值
                    Field field = brandEntity.getClass().getDeclaredField(fieldNames.get(j));
                    field.setAccessible(true);
                    String fieldValue =  String.valueOf(field.get(brandEntity));
                    System.out.println(fieldNames.get(j)+"1111111111"+fieldValue);
                    cell.setCellValue(fieldValue);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }
        try{
            FileOutputStream outputStream = new FileOutputStream("H:\\PoiFile\\test.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }
    @RequestMapping("/test")
    //@RequiresPermissions("product:brand:save")
    public R test( @Valid @RequestBody BrandEntity brand/*, BindingResult result*/){
//        if(result.hasErrors()){
//            Map<String,String> map = new HashMap<>();
//            //获取校验的错误结果
//            result.getFieldErrors().forEach((item)->{
//                //获取错误信息
//                String message = item.getDefaultMessage();
//                //获取错误字段
//                String field = item.getField();
//                map.put(field,message);
//            });
//            return R.error(400, "提交的数据不合法").put("data",map);
//
//
//        }else{
        brandService.save(brand);

        return R.ok();
        // }

    }
    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    public R save( @Validated(AddGroup.class) @RequestBody BrandEntity brand/*, BindingResult result*/){
//        if(result.hasErrors()){
//            Map<String,String> map = new HashMap<>();
//            //获取校验的错误结果
//            result.getFieldErrors().forEach((item)->{
//                //获取错误信息
//                String message = item.getDefaultMessage();
//                //获取错误字段
//                String field = item.getField();
//                map.put(field,message);
//            });
//            return R.error(400, "提交的数据不合法").put("data",map);
//
//
//        }else{
            brandService.save(brand);

            return R.ok();
       // }

    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){

        brandService.updateById(brand);

        return R.ok();

    }
    @RequestMapping("/updateShowStatus")
    //@RequiresPermissions("product:brand:update")
    public R updateShowStatus(@Validated(UpdateField.class) @RequestBody BrandEntity brand){

        brandService.updateById(brand);

        return R.ok();

    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
