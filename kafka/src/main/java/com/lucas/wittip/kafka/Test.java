package com.lucas.wittip.kafka;

import com.google.common.cache.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: liucaisi
 * @date: 2017/12/27
 */
public class Test {
    private static LoadingCache<String, String> cacheBuilder = CacheBuilder.newBuilder().maximumSize(1)
            .expireAfterWrite(1, TimeUnit.SECONDS).refreshAfterWrite(2, TimeUnit.MILLISECONDS).removalListener(
                    (RemovalListener<String, String>) notification -> System.out.println(notification.getKey() + " 被移除")).build(
                            new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    System.out.println("load key:" + key);
                    return key;
                }
            });


    public static void main(String[] args) throws ExecutionException, InterruptedException {

    }

}
