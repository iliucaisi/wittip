package com.lucas.instrumentation;

import java.util.concurrent.TimeUnit;

/**
 * @file: Lion.java
 * @author: caisil
 * @date: 2018/1/13
 */
public class Lion {
    public void runLion() throws InterruptedException {
        System.out.println("Lion is going to run...");
        TimeUnit.SECONDS.sleep(2);
    }
}
