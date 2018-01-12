package com.lucas.wittip.kafka;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: liucaisi
 * @date: 2018/1/9
 */
public class App {
    private String attr1;
    private String atrr2;

    public static void main(String[] args) {
        try {
            scheduler();
        } catch (Exception e) {
            System.out.println("hello");
        }
    }

    public static void scheduler() {

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                } finally {
                    service.schedule(this, 10, TimeUnit.SECONDS);
                }
                return null;
            }

        };
        service.schedule(callable, 0, TimeUnit.SECONDS);
    }
}
