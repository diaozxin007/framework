package com.xilidou.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.lang.Thread.UncaughtExceptionHandler;


@Slf4j
public class LoggingUncaughtExceptionHandler implements UncaughtExceptionHandler {

    private LoggingUncaughtExceptionHandler(){
    }

    private static final LoggingUncaughtExceptionHandler SINGLETON = new LoggingUncaughtExceptionHandler();

    public static LoggingUncaughtExceptionHandler getSingleton(){
        return SINGLETON;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof RuntimeException && e.getCause() != null) {
            if (e.getCause() instanceof InterruptedException) Thread.currentThread().interrupt();
            log.error(t.getName(), e.getCause());
            return;
        }
        log.error(t.getName(), e);
    }

}
