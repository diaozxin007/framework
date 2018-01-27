package com.xilidou.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class Flusher<Item> {

    private static ScheduledExecutorService TIMER = new ScheduledThreadPoolExecutor(1,new DebugableThreadFactory("scheduled-thread"));

    public Flusher() {



    }

    private static class FlushThread<Item> implements Runnable{

        private final String name;
        private final int bufferSize;
        private int flushInterval;

        private volatile long lastFlushTime;
        private volatile Thread writer;

        private final BlockingQueue<Item> queue;
        private final Processor<Item> processor;

        public FlushThread(String name, int bufferSize, int flushInterval,int queueSize,Processor processor) {
            this.name = name;
            this.bufferSize = bufferSize;
            this.flushInterval = flushInterval;
            this.lastFlushTime = System.currentTimeMillis();
            this.processor = processor;

            this.queue = new ArrayBlockingQueue<>(queueSize);

        }

        public void add(Item item){
            queue.offer(item);

        }

        public boolean flushOnDemand(){

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
            if(queue.size() > bufferSize || System.currentTimeMillis() - lastFlushTime > flushInterval){
                return true;
            }
            return false;
        }

        @Override
        public void run() {

        }
    }
}
