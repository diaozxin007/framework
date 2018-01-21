package com.xilidou.framework.aop.test;

import com.xilidou.framework.aop.core.AopApplictionContext;

public class MainTest {

    public static void main(String[] args) throws Exception {

        AopApplictionContext aopApplictionContext = new AopApplictionContext("application.json");
        aopApplictionContext.init();

        TestService testService = (TestService) aopApplictionContext.getBean("testServiceProxy");

        testService.testMethod();


    }
}
