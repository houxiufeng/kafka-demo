package com.example.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

//1.向容器注册一个KafkaListenerErrorHandler类型的bean
//2.该bean就是当处理消息异常的时候，将消息加入到.DLT主题中
@Component("kafkaListenerErrorHandler")
public class KafkaListenerErrorHandlerTest implements KafkaListenerErrorHandler {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC_DLT=".DLT";

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
        System.out.println("消费失败消息:"+message.toString());
        System.out.println("exception:" + exception);
        //获取消息处理异常主题
        MessageHeaders headers = message.getHeaders();
        String topic=headers.get("kafka_receivedTopic")+TOPIC_DLT;
        System.out.println("handleError topic is:" + topic);
        //放入死讯队列
        kafkaTemplate.send(topic, message.getPayload());
        return message;
    }
}
