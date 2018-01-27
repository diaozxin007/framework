package com.xilidou.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 可以记录异常日志
 */
public class DebugableThreadFactory implements ThreadFactory {

    private final boolean     daemon;
    private final ThreadGroup group;
    private final String      namePrefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public DebugableThreadFactory(final String name) {
        this(name, false);
    }

    public DebugableThreadFactory(final String name, final boolean daemon) {
        this.daemon = daemon;
        final SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = name + "-thread-";
    }

    @Override
    public Thread newThread(final Runnable r) {
        final Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (daemon) { t.setDaemon(daemon); }
        if (t.getPriority() != Thread.NORM_PRIORITY) { t.setPriority(Thread.NORM_PRIORITY); }
        t.setUncaughtExceptionHandler(LoggingUncaughtExceptionHandler.getSingleton());
        return t;
    }
}
