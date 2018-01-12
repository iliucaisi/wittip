package com.lucas.wittip.kafka.launch;

import com.lucas.wittip.kafka.core.KafkaMailProducer;
import com.lucas.wittip.kafka.util.KafkaExampleFileUtil;
import com.lucas.wittip.kafka.util.KafkaExampleProperty;
import com.lucas.wittip.kafka.util.KafkaExamplePropertyKey;
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
public class MailProducerDemo implements KafkaMailProperties{
    private static final String PATH = "path";
    private static final String TOPIC_NAME = "topic";

    public static void main(String[] args) throws ParseException {
        KafkaExampleCommandLineHandler commandLineHandler = new KafkaExampleCommandLineHandler(getProducerOptions(),
                args);

        String topic = commandLineHandler.getOption(TOPIC_NAME);
        String path = commandLineHandler.getOption(PATH);

        KafkaMailProducer producer = new KafkaMailProducer(topic != null ? topic : KafkaMailProperties.topic1, path
                != null ? KafkaExampleFileUtil.getValidDirectoryPath(path) : KafkaExampleProperty.getPropertyValue
                (KafkaExamplePropertyKey.MAIL_DIRECTORY));

        producer.start();
    }

    private static List<Option> getProducerOptions() {
        List<Option> optionList = new ArrayList<>();
        Option topicOption = new Option(TOPIC_NAME, TOPIC_NAME, true, "topic name on which message is going to be " +
                "published.");
        Option pathOption = new Option(PATH, PATH, true, "directory path from where message content going to be " +
                "consumed.");

        optionList.add(topicOption);
        optionList.add(pathOption);
        return optionList;
    }
}
