package com.xilidou.framework.ioc.bean;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConstructorArg {

    private int index;
    private String ref;
    private String name;

}
