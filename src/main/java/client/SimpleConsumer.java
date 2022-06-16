package client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;


public class SimpleConsumer {
    static String topicName = "test";

    public static void main (String[] args) {

        Properties cfg = new Properties();
        cfg.setProperty("bootstrap.servers", "localhost:29093");
        cfg.setProperty("group.id", "test");
        cfg.setProperty("enable.commit.interval.ms", "1000");
        cfg.setProperty("session.timout.ms", "30000");
        cfg.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        cfg.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(cfg);
        System.out.printf("Subscribed to topic " + topicName);
        consumer.subscribe(Arrays.asList(topicName));

        while (true) {
            ConsumerRecords<String, String> recs = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : recs) {
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
            }
        }
    }




}
