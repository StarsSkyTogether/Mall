package com.wch.mall.product.controller;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;


import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import com.wch.common.valid.AddGroup;
import com.wch.common.valid.UpdateField;
import com.wch.common.valid.UpdateGroup;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.wch.mall.product.entity.BrandEntity;
import com.wch.mall.product.service.BrandService;
import com.wch.common.utils.PageUtils;
import com.wch.common.utils.R;
import sun.security.mscapi.CKeyPairGenerator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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
    /**
    //导出报表1
    @PostMapping("/exportingReport1")

    public R exportingReport1(@RequestBody  String[] fields){
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


    //导出报表2
    @PostMapping("/exportingReport2")
    public void exportingReport2(@RequestBody  String[] fields, HttpServletResponse response){
        HashMap<String, String> fieldMap = new HashMap<>();
        for(String str : fields){
            switch (str){
                case "1": fieldMap.put("brandId","品牌id");break;
                case "2": fieldMap.put("name","品牌名");break;
                case "3": fieldMap.put("logo","品牌logo地址");break;
                case "4": fieldMap.put("descript","介绍");break;
                case "5": fieldMap.put("showStatus","显示状态");break;
                case "6": fieldMap.put("firstLetter","检索首字母");break;
                case "7": fieldMap.put("sort","排序");break;
                default: break;
            }
        }
        Set<String> fieldNames = fieldMap.keySet();
        List<BrandEntity> brandEntities = brandService.queryAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //设置表头
        Row headRow = sheet.createRow(0);
        int c=0;
        for(String fieldName : fieldNames){
            Cell cell = headRow.createCell(c++);
            cell.setCellValue(fieldMap.get(fieldName));
        }
        //设置表的内容
        for(int i=0; i<brandEntities.size(); i++){
            BrandEntity brandEntity = brandEntities.get(i);
            Row row = sheet.createRow(i+1);
            int j=0;
            for(String fieldName : fieldNames){
                Cell cell = row.createCell(j++);
                try{
                    //通过反射获取相应属性名的属性值
                    Field field = brandEntity.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    String fieldValue =  String.valueOf(field.get(brandEntity));
                    cell.setCellValue(fieldValue);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }

        OutputStream outputStream = null;
        try{
            this.setResponseHeader(response,"hahah");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                outputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }
     */

    private Workbook excelStreamCreate(Map<String,String> fieldMap,List<?> list){
        Set<String> fieldNames = fieldMap.keySet();

        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //设置表头
        Row headRow = sheet.createRow(0);
        int c=0;
        for(String fieldName : fieldNames){
            Cell cell = headRow.createCell(c++);
            cell.setCellValue(fieldMap.get(fieldName));
        }
        //设置表的内容
        for(int i=0; i<list.size(); i++){
            Object t = list.get(i);
            Row row = sheet.createRow(i+1);
            int j=0;
            for(String fieldName : fieldNames){
                Cell cell = row.createCell(j++);
                try{
                    //通过反射获取相应属性名的属性值
                    Field field = t.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    String fieldValue =  String.valueOf(field.get(t));
                    cell.setCellValue(fieldValue);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
        return workbook;
    }
    //导出报表3
    @PostMapping("/exportingReport3")
    public void exportingReport3(@RequestBody  Map<String,Object> map, HttpServletResponse response){
        System.out.println("11111111111111");
        System.out.println(map);
        ArrayList<String> fields =(ArrayList<String>) map.get("fields");
        System.out.println(fields);


        System.out.println("22222222222222");
        String key = (String) map.get("key");
        System.out.println("33333333333333333");
        HashMap<String, String> fieldMap = new HashMap<>();
        for(String str : fields){
            switch (str){
                case "1": fieldMap.put("brandId","品牌id");break;
                case "2": fieldMap.put("name","品牌名");break;
                case "3": fieldMap.put("logo","品牌logo地址");break;
                case "4": fieldMap.put("descript","介绍");break;
                case "5": fieldMap.put("showStatus","显示状态");break;
                case "6": fieldMap.put("firstLetter","检索首字母");break;
                case "7": fieldMap.put("sort","排序");break;
                default: break;
            }
        }
        List<BrandEntity> list = brandService.queryAll(key);
        System.out.println(fieldMap);
        Workbook workbook = excelStreamCreate(fieldMap, list);

        OutputStream outputStream = null;
        try{
            this.setResponseHeader(response,"hahah");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                outputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }


    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
