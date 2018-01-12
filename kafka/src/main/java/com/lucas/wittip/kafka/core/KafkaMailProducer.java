package com.lucas.wittip.kafka.core;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

/**
 * @author: liucaisi
 * @date: 2017/12/6
 */
public class KafkaMailProducer extends Thread {
    private final Producer<Integer, String> producer;
    private final String topic;
    private final String directoryPath;
    private final Properties properties = new Properties();

    public KafkaMailProducer(String topic, String directoryPath) {
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("metadata.broker.list", "localhost:9092");
        this.producer = new KafkaProducer<Integer, String>(properties);
        this.topic = topic;
        this.directoryPath = directoryPath;
    }

    @Override
    public void run() {
        Path dir = Paths.get(directoryPath);

        new WatchDir(dir).start();
        new ReadDir(dir).start();
    }


    private class WatchDir extends Thread{
        private WatchKey key;
        private final Path directory;
        private WatchService watchService;

        WatchDir(Path directory) {
            this.directory = directory;
            try {
                this.watchService = FileSystems.getDefault().newWatchService();
                this.key = directory.register(watchService, ENTRY_CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        <T> WatchEvent<T> cast(WatchEvent<?> event) {
            return (WatchEvent<T>) event;
        }

        @Override
        public void run() {
            for (;;) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    if (kind == OVERFLOW) {
                       continue;
                    }

                    WatchEvent<Path> ev = cast(event);
                    Path name = ev.context();
                    Path child = directory.resolve(name);

                    if (kind == ENTRY_CREATE) {
                        if (Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS)) {
                            try {
                                readFileContent(child.toFile());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        }
    }

    private class ReadDir extends Thread{
        private final Path directory;

        public ReadDir(Path directory) {
            this.directory = directory;
        }

        @Override
        public void start() {
            File file = directory.toFile();

            List<File> dirMessages = Arrays.asList(file.listFiles(pathname -> {
                if (pathname == null) {
                    return false;
                }

                if (pathname.isFile() && !pathname.isHidden()) {
                    return true;
                }
                return false;
            }));

            for (File temp : dirMessages) {
                try {
                    readFileContent(temp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readFileContent(File file) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile(file, "r");
        FileChannel inChannel = accessFile.getChannel();
        MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        buffer.load();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < buffer.limit(); i++) {
            builder.append((char) buffer.get());
        }

        buffer.clear();
        inChannel.close();
        accessFile.close();

        producer.send(new ProducerRecord<Integer, String>(topic, builder.toString()));
        System.out.println(file.getAbsolutePath() + " - content consumed.");

        file.delete();
    }
}
