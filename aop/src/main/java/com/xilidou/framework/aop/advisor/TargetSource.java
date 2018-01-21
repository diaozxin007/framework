package com.xilidou.framework.aop.advisor;

import lombok.Data;

@Data
public class TargetSource {

    private Class<?> tagetClass;

    private Object tagetObject;

}
