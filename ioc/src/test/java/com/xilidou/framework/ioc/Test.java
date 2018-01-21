package com.xilidou.framework.ioc;

import com.xilidou.framework.ioc.core.JsonApplicationContext;
import com.xilidou.framework.ioc.entity.Robot;

public class Test {

    public static void main(String[] args) throws Exception {

        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Robot aiRobot = (Robot) applicationContext.getBean("robot");

        aiRobot.show();

    }

}
