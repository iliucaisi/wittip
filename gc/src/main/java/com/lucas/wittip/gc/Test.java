package com.lucas.wittip.gc;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * VM options: -verbose:gc -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+UseSerialGC
 * -XX:+PrintTenuringDistribution -XX:InitialTenuringThreshold=1 -XX:MaxTenuringThreshold=1
 * @author: liucaisi
 * @date: 2018/1/15
 */
public class Test {
    private static final int _1M_SIZE = 1024 * 1024;
    private static Map<String, byte[]> strMap = Maps.newHashMap();

    public static void test() {
        strMap.putIfAbsent("a", new byte[2 * _1M_SIZE]);
        strMap.putIfAbsent("b", new byte[2 * _1M_SIZE]);
        strMap.putIfAbsent("c", new byte[2 * _1M_SIZE]);

        strMap.putIfAbsent("d", new byte[3 * _1M_SIZE]);
    }

    public static void main(String[] args) {
        test();
    }
}
