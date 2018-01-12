package com.lucas.wittip.kafka.util;

import org.apache.commons.cli.*;

import java.util.List;

/**
 * @author: liucaisi
 * @date: 2017/12/6
 */
public class KafkaExampleCommandLineHandler {
    private final CommandLine commandLine;

    public KafkaExampleCommandLineHandler(final List<Option> optionList, String[] args) throws ParseException {
        final CommandLineParser parser = new DefaultParser();
        final Options options = new Options();
        for (Option option : optionList) {
           options.addOption(option);
        }

        commandLine = parser.parse(options, args);
    }

    public String getOption(String option) {
        if (commandLine != null && commandLine.hasOption(option)) {
            return commandLine.getOptionValue(option);
        }

        return null;
    }
}
