package com.chase.kafkaproject.controller;

import com.chase.kafkaproject.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

@RestController
public class KafkaController {
    @Autowired
    Producer producer;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<?> send(@RequestParam("msg") String msg) throws ExecutionException, InterruptedException {
        IntStream.range(0,1000).forEach(i -> {
            try {
                producer.publish(msg +i);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.publish(msg);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
