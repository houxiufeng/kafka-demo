package com.example.demo.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * 消费者（监听topic队列）
 * 题外话：想要动态创建参考： https://www.volcengine.com/theme/7157047-OTHER-7-1
 *
 */
@Component
public class ConsumerListener2 {

    private static Logger logger = LoggerFactory.getLogger(ConsumerListener2.class);

    @KafkaListener(topics = "fitz_test_topic", groupId = "fitz_group1")
    public void listen(ConsumerRecord<?, String> record) {
        System.out.println(record);
        String value = record.value();
        logger.info("fitz_group1 receive msg:"+ value);
    }

    /**
     * 不同的消费组，并且设置并发度为3，是因为有3个partition, 每个partition 一个consumer
     * @param record
     * @param ack
     */
    @KafkaListener(topics = "fitz_test_topic", groupId = "fitz_group2", concurrency = "3")
    public void listen2(ConsumerRecord<?, String> record, Acknowledgment ack) {
        System.out.println(record);
        String value = record.value();
        logger.info("fitz_group2 receive msg:"+ value);
        ack.acknowledge();
    }

}
