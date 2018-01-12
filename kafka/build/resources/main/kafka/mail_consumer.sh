#!/usr/bin/env bash

export KAFKA_HEAP_OPTS="-Xmx512M"
exec /opt/kafka/bin/kafka-run-class.sh com.lucas.pittiw.kafka.launch.MailConsumerDemo $@