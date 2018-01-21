package com.xilidou.framework.ioc.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * @author Zhengxin
 */
@Data
@ToString
public class BeanDefinition {

    private String name;

    private String className;

    private String[] interfaceName;

    private List<ConstructorArg> constructorArgs;

    private List<PropertyArg> propertyArgs;

}
