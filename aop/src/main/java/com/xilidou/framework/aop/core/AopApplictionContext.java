package com.xilidou.framework.aop.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xilidou.framework.aop.bean.AopBeanDefinition;
import com.xilidou.framework.ioc.bean.BeanDefinition;
import com.xilidou.framework.ioc.utils.ClassUtils;
import com.xilidou.framework.ioc.utils.JsonUtils;

import java.io.InputStream;
import java.util.List;

public class AopApplictionContext extends AopBeanFactoryImpl {

    private String fileName;

    public AopApplictionContext(String fileName) {
        this.fileName = fileName;
    }

    public void init(){
        loadFile();
    }

    private void loadFile(){

        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        List<AopBeanDefinition> beanDefinitions = JsonUtils.readValue(is,new TypeReference<List<AopBeanDefinition>>(){});

        if(beanDefinitions != null && !beanDefinitions.isEmpty()) {

            for (AopBeanDefinition beanDefinition : beanDefinitions){
                Class<?> clz = ClassUtils.loadClass(beanDefinition.getClassName());
                if(clz == ProxyFactoryBean.class){
                    registerBean(beanDefinition.getName(),beanDefinition);
                }else {
                    registerBean(beanDefinition.getName(),(BeanDefinition) beanDefinition);
                }
            }
        }

    }


}
