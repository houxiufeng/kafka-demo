package com.example.demo.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * 关于错误处理: https://juejin.cn/post/6973808491475501070
 * 默认情况下，什么都不做的话，会重试10次，然后丢弃这条msg.
 * retry 方式已经被errorHandler取代，不推荐再用。
 */
@Component
public class ConsumerListener3 {

    private static Logger logger = LoggerFactory.getLogger(ConsumerListener3.class);


    /**
     * 默认行为，不处理错误, 重试10次， 然后丢弃这条msg.
     */
    @KafkaListener(topics = "error_test_topic2", groupId = "fitz_group3", concurrency = "1")
    public void listen(String msg,Acknowledgment ack) {
        businessProcess(msg);
        ack.acknowledge();
    }

    /**
     * 不同的消费组，并且设置并发度为3，是因为有3个partition, 每个partition 一个consumer
     */
    @KafkaListener(topics = "error_test_topic", groupId = "fitz_group3", concurrency = "3", errorHandler ="kafkaListenerErrorHandler")
    public void listenWithErrorHandler(String msg,Acknowledgment ack) {
        try {
            businessProcess(msg);
        } finally {
            //手动提交
            ack.acknowledge();
        }
    }

    //1.专门处理死讯队列消息，都是topicName+.DLT的主题
    //2.死讯队列里，只有消费成功的才提交offset,否则等待bug修复完上线，继续处理
    @KafkaListener(topics = {"error_test_topic.DLT"},groupId = "fitz_group3", concurrency = "1")
    public void test7(String msg,Acknowledgment ack){
        try {
            businessProcess(msg);
            ack.acknowledge();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void businessProcess(String msg){
        System.out.println("接收到消息:" + msg + "--" + System.currentTimeMillis() + "---" + Thread.currentThread().hashCode());

        if (Integer.valueOf(msg) % 5 == 0) {
            int i = 1 / 0;
        }
    }


}
