package com.chase.entity;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaAvorProducer {

    public static void main(String arg[]) {
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers", "localhost:9092");
        prop.setProperty("acks", "1");
        prop.setProperty("retries", "10");
        prop.setProperty("key.serializer", StringSerializer.class.getName());
        prop.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        prop.setProperty("schema.registry.url", "http://localhost:8081");
        KafkaProducer<String , Employee> producer = new KafkaProducer<String, Employee>(prop);
        String topic = "testTopic";
        Employee employee = Employee.newBuilder().setAge(38)
                .setName("alok")
                .build();
        ProducerRecord<String, Employee> producerRecord = new ProducerRecord<String, Employee>(topic,employee);
        Future<RecordMetadata> send = producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(e ==null){
                    System.out.println("Success");
                    System.out.println(recordMetadata.toString());
                }else {
                    e.printStackTrace();
                }

            }
        });



    }
}
