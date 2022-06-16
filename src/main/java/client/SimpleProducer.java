package client;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class SimpleProducer {
    public static void main(String[] args) {
        String topicName = "testtopic";

        Properties cfg = new Properties();
        cfg.setProperty("bootstrap.servers", "localhost:29093");
        cfg.setProperty("acks", "all");
        cfg.setProperty("retries", "0");
        cfg.setProperty("batch.size", "16384");
        cfg.setProperty("linger.ms", "1");
        cfg.setProperty("buffer.memory", "33554432");
        cfg.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        cfg.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(cfg);

        for (int i=0; i< 10; i++) {
            producer.send(new ProducerRecord<String, String>(topicName, "key" + Integer.toString(i), "event" + Integer.toString(i)));
        }

        System.out.println("Message sent successfuly");

        producer.close();

    }
}
