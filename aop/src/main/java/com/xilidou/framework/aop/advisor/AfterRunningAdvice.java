package com.xilidou.framework.aop.advisor;

import java.lang.reflect.Method;

/**
 * @author Zhengxin
 */
public interface AfterRunningAdvice extends Advice{
    Object after(Object returnVal,Method method, Object[] args, Object target);
}
