package com.xilidou.test;

import com.xilidou.concurrent.Flusher;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        Flusher<String> stringFlusher = new Flusher<>("test",5,2000,30,1,new PrintOutProcessor());

        int index = 1;
        while (true){
            stringFlusher.add(String.valueOf(index++));
            Thread.sleep(10);
        }
    }
}
