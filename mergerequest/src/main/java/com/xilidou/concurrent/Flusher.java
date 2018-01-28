package com.xilidou.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Flusher<Item> {

    private final FlushThread<Item>[] flushThreads;

    private AtomicInteger index;

    private static final Random r = new Random();

    private static final int delta = 50;


    private static ScheduledExecutorService TIMER = new ScheduledThreadPoolExecutor(1);

    private static ExecutorService POOL = Executors.newCachedThreadPool();

    public Flusher(String name,int bufferSiz,int flushInterval,int queueSize,int threads,Processor<Item> processor) {

        this.flushThreads = new FlushThread[threads];


        if(threads > 1){
            index = new AtomicInteger();
        }

        for (int i = 0; i < threads; i++) {
            final FlushThread<Item> flushThread = new FlushThread<Item>(name+ "-" + i,bufferSiz,flushInterval,queueSize,processor);
            flushThreads[i] = flushThread;
            POOL.submit(flushThread);
            TIMER.scheduleAtFixedRate(flushThread::timeOut, r.nextInt(delta), flushInterval, TimeUnit.MILLISECONDS);
        }
    }

    public boolean add(Item item){
        int len = flushThreads.length;
        if(len == 1){
            return flushThreads[0].add(item);
        }

        int mod = index.incrementAndGet() % len;
        return flushThreads[mod].add(item);

    }

    private static class FlushThread<Item> implements Runnable{

        private final String name;
        private final int bufferSize;
        private int flushInterval;

        private volatile long lastFlushTime;
        private volatile Thread writer;

        private final BlockingQueue<Item> queue;
        private final Processor<Item> processor;

        public FlushThread(String name, int bufferSize, int flushInterval,int queueSize,Processor<Item> processor) {
            this.name = name;
            this.bufferSize = bufferSize;
            this.flushInterval = flushInterval;
            this.lastFlushTime = System.currentTimeMillis();
            this.processor = processor;

            this.queue = new ArrayBlockingQueue<>(queueSize);

        }

        public boolean add(Item item){
            boolean result = queue.offer(item);
            flushOnDemand();
            return result;
        }

        public void timeOut(){
            if(System.currentTimeMillis() - lastFlushTime >= flushInterval){
                start();
            }
        }

        private void start(){
            LockSupport.unpark(writer);
        }

        private void flushOnDemand(){
            if(queue.size() > bufferSize){
                start();
            }
        }

        public void flush(){
            lastFlushTime = System.currentTimeMillis();
            List<Item> temp = new ArrayList<>(bufferSize);
            int size = queue.drainTo(temp,bufferSize);
            if(size > 0){
                try {
                    processor.process(temp);
                }catch (Throwable e){
                    log.error("process error",e);
                }
            }
        }

        private boolean canFlush(){
            return queue.size() > bufferSize || System.currentTimeMillis() - lastFlushTime > flushInterval;
        }

        @Override
        public void run() {
            writer = Thread.currentThread();
            writer.setName(name);

            while (!writer.isInterrupted()){
                while (!canFlush()){
                    LockSupport.park(this);
                }
                flush();
            }

        }

    }
}
