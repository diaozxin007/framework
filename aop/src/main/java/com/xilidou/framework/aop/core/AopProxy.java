package com.xilidou.framework.aop.core;

public interface AopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);

}
