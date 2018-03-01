package com.xilidou.framework.ioc.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class Mouth {

    private Hand hand;

    public void speak(){

        System.out.println("say hello world");

    }

}
