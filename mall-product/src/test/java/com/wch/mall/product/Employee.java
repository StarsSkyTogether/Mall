package com.wch.mall.product;


import org.springframework.beans.factory.annotation.Required;

public class Employee {
    private String name;
    @Required
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}