package com.xilidou.framework.aop.advisor;


import lombok.Data;

@Data
public class Advisor {

    private Advice advice;

    private Pointcut pointcut;

}
