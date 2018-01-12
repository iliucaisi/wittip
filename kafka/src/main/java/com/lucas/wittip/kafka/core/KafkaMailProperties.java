package com.lucas.wittip.kafka.core;

/**
 * @author: liucaisi
 * @date: 2017/12/6
 */
public interface KafkaMailProperties {
    String topic1 = "topic1";
    String groupId = "group1";
    String zkConnect = "127.0.0.1:2181";
    String kafkaServerUrl = "localhost";

    int kafkaServerPort = 9092;
    int kafkaProducerBufferSize = 64 * 1024;
    int connectionTimeOut = 100000;
    int reconnectInterval = 10000;

    String topic2 = "topic2";
    String topic3 = "topic3";
    String clientId = "MailConsumerDemoClient";
}
