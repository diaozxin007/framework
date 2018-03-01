package com.xilidou.framework.ioc.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Robot {

    private Hand hand;

    private Mouth mouth;

    public void show(){

        hand.waveHand();
        mouth.speak();

    }

}
