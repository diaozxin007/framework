package com.xilidou.framework.aop.interceptor;

import com.xilidou.framework.aop.Invocation.MethodInvocation;
import com.xilidou.framework.aop.advisor.BeforeMethodAdvice;

public class BeforeMethodAdviceInterceptor implements AopMethodInterceptor {

    private BeforeMethodAdvice advice;

    public BeforeMethodAdviceInterceptor(BeforeMethodAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        advice.before(mi.getMethod(),mi.getArguments(),mi);
        return mi.proceed();
    }
}
