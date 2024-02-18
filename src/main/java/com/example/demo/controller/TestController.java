package com.example.demo.controller;

import com.example.demo.config.KafkaConfiguration;
import com.example.demo.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private KafkaService kafkaService;
    private KafkaConfiguration kafkaConfiguration;

    @Autowired
    public TestController (KafkaService kafkaService, KafkaConfiguration kafkaConfiguration) {
        this.kafkaService = kafkaService;
        this.kafkaConfiguration = kafkaConfiguration;
    }

    @RequestMapping("send")
    public String sendMsg(String msg) {
        kafkaService.send(kafkaConfiguration.getMyTopic1(), msg);
        return kafkaConfiguration.getMyTopic1() + " ok";
    }

    @RequestMapping("send2")
    public String sendMsg2(String msg) {
        kafkaService.send(kafkaConfiguration.getMyTopic2(), msg);
        return kafkaConfiguration.getMyTopic2() + " ok";
    }

    @RequestMapping("send3")
    public String sendMsg3(String msg) {
        kafkaService.send("fitz_test_topic", msg);
        return "fitz_test_topic ok";
    }

    @RequestMapping("send/{topic}/{msg}")
    public String sendMsgToTopic(@PathVariable String topic, @PathVariable String msg) {
        kafkaService.send(topic, msg);
        return "sendMsgToTopic ok";
    }


}
