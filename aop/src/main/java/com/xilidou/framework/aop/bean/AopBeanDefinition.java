package com.xilidou.framework.aop.bean;

import com.xilidou.framework.ioc.bean.BeanDefinition;
import lombok.Data;

import java.util.List;

@Data
public class AopBeanDefinition extends BeanDefinition{

    private String target;

    private List<String> interceptorNames;

}
