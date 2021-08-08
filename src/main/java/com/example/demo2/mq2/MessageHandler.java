package com.example.demo2.mq2;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    //1. 手动创建，需在RabbitMQ中手动创建myQueue1 队列，否则报错
//    @RabbitListener(queues = "monkey-test-queue")

    //2. 自动创建队列
    //@RabbitListener(queuesToDeclare = @Queue("myQueue2"))

    //3. 自动创建队列，Exchange 与 Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value ="test-Queue", durable = "true"),
            exchange = @Exchange(value="test-ExChange",durable = "true", type="topic"),
              key = "test.*"
    ))
    public void handleMessage(Message message){
        System.out.println("====消费消息===handleMessage(message)");
        System.out.println(message.getMessageProperties());
        System.out.println(new String(message.getBody()));
    }
}