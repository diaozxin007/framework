package com.xilidou.framework.ioc.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Hand {

    private Mouth mouth;

    public void waveHand(){

        System.out.println("挥一挥手");

    }

}
