package com.chase.kafkaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
@Service
public class Producer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    public static Integer i = 0;
    public static final String topic = "topic01";
    public void publish(String str) throws ExecutionException, InterruptedException {
        System.out.println("this is from publisher ");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, i++, "a", str);
        if(i == 3){
            i=0;
        }
        while(!future.isDone()){
           // System.out.println("Still not evaluated");
        }
        System.out.println(future.get().getRecordMetadata().toString());

    }
}
