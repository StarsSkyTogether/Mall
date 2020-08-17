package com.wch.mall.product.exception;

import com.wch.common.exception.BizCodeEnum;
import com.wch.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "com.wch.mall.product.controller")
@Slf4j
public class MallControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e){

        log.error("数据校验出现问题{}，异常类型：{}",e.getMessage(),e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> errorMap = new HashMap<>();

        bindingResult.getFieldErrors().forEach((fieldError) ->{
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorMap.put(field,message);
        });


        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(),BizCodeEnum.VALID_EXCEPTION.getMessage()).put("data",errorMap);
    }

    @ExceptionHandler(Throwable.class)
    public R handleException(Throwable throwable){
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.VALID_EXCEPTION.getMessage());
    }



}
