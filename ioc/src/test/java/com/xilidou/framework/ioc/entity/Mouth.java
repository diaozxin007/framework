package com.xilidou.framework.ioc.entity;

import lombok.Data;

@Data
public class Mouth {

    private Hand hand;

    private String code;

    public void speak() {
        System.out.println("mouth 编号：" + code + ",依赖于hand 编号" + hand.getCode());
        System.out.println("say hello world");
    }
}
