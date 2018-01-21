package com.xilidou.framework.aop.adapter;

import com.xilidou.framework.aop.advisor.Advisor;
import com.xilidou.framework.aop.interceptor.AopMethodInterceptor;

public interface AdviceAdapter {

    AopMethodInterceptor getInterceptor(Advisor advisor);
}
