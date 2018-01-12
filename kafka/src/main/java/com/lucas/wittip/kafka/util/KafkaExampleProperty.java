package com.lucas.wittip.kafka.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: liucaisi
 * @date: 2017/12/6
 */
public class KafkaExampleProperty {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(KafkaExampleProperty.class.getResourceAsStream("/kafkaMailServer.prop"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyValue(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}
