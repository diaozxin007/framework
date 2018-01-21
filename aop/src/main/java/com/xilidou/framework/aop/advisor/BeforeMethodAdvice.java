package com.xilidou.framework.aop.advisor;

import java.lang.reflect.Method;

/**
 * @author Zhengxin
 */
public interface BeforeMethodAdvice extends Advice{
    void before(Method method, Object[] args, Object target);
}
