package com.lucas.instrumentation;

import java.lang.instrument.Instrumentation;

/**
 * @author: liucaisi
 * @date: 2018/1/12
 */
public class DurationAgent {
    public static void premain(String agentArgs, Instrumentation
            instrumentation) {
        System.out.println("Executing premain...");
        instrumentation.addTransformer(new DurationTransformer());
    }

}
