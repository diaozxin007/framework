package com.xilidou.test;

import com.xilidou.concurrent.Processor;

import java.util.List;

public class PrintOutProcessor implements Processor<String>{
    @Override
    public void process(List<String> list) {

        System.out.println("start flush");

        list.forEach(System.out::println);

        System.out.println("end flush");
    }
}
