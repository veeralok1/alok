package com.chase.kafkaproject.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Consumer1 {

    @KafkaListener(id = "id1", topics="topic01", groupId = "myGroup")
    public void consumeFromTopic(@Payload List<String> msgs, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        System.out.println("batch size ::"+ msgs.size());
        for(int i=0; i < msgs.size(); i++){
            System.out.println("consumed message :: "+ msgs.get(i) + " from consumer 1 in partition :: "+partitions.get(i) + " Now current offset is :: " + offsets.get(i));

        }
    }

}
