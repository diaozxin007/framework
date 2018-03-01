package com.xilidou.framework.ioc.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PropertyArg {

    private String name;

    private String value;

    private String typeName;

    private String ref;
}
