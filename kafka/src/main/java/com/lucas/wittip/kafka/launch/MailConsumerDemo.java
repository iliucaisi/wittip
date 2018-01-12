package com.lucas.wittip.kafka.launch;

import com.lucas.wittip.kafka.core.KafkaMailConsumer;
import com.lucas.wittip.kafka.core.KafkaMailProperties;
import com.lucas.wittip.kafka.util.KafkaExampleCommandLineHandler;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liucaisi
 * @date: 2017/12/6
 */
public class MailConsumerDemo {
    private static final String TOPIC_NAME = "topic";

    public static void main(String[] args) throws ParseException {
        KafkaExampleCommandLineHandler commandLineHandler = new KafkaExampleCommandLineHandler(getConsumerOptions(),
                args);

        String topic = commandLineHandler.getOption(TOPIC_NAME);

        KafkaMailConsumer consumer = new KafkaMailConsumer(topic != null ? topic : KafkaMailProperties.topic1);
        consumer.start();
    }

    private static List<Option> getConsumerOptions() {
        List<Option> optionList = new ArrayList<>();
        Option topicOption = new Option(TOPIC_NAME, TOPIC_NAME, true, "topic name on which message is going to be " +
                "published.");
        optionList.add(topicOption);
        return optionList;
    }
}
