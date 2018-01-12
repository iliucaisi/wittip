package com.lucas.wittip.kafka.core;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: liucaisi
 * @date: 2017/12/5
 */
public class KafkaMailConsumer extends Thread{
    private final Consumer<Integer, String> consumer;
    private final String topic;

    public KafkaMailConsumer(String topic) {
        Properties properties = new Properties();
        properties.put("zookeeper.connect", KafkaMailProperties.zkConnect);
        properties.put("group.id", KafkaMailProperties.groupId);
        properties.put("zookeeper.session.timeout.ms", "400");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("auto.commit.interval.ms", "1000");
        consumer = new KafkaConsumer<Integer, String>(properties);
       this.topic = topic;
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, new Integer(1));
        consumer.subscribe(Arrays.asList(topic));

        while (true) {
            ConsumerRecords<Integer, String> records = consumer.poll(100);
            for (ConsumerRecord<Integer, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}
