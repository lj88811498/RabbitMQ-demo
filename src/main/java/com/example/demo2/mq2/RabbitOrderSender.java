package com.example.demo2.mq2;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;


@Controller
//@Component
public class RabbitOrderSender {
    //自动注入RabbitTemplate模板类
    @Autowired
    private RabbitTemplate rabbitTemplate;


    //回调函数: confirm确认
//    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
//        @Override
//        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//            System.err.println("correlationData: " + correlationData);
//            String messageId = correlationData.getId();
//            if(ack){
//                //如果confirm返回成功 则进行更新
//                System.out.println("成功了~~~~~~！！！！messageId=" + messageId);
//            } else {
//                //失败则进行具体的后续操作:重试 或者补偿等手段
//                System.err.println("异常处理...");
//            }
//        }
//    };

    //发送消息方法调用: 构建自定义对象消息
    @GetMapping("mq")
    @ResponseBody
    public void sendOrder() throws Exception {
        // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
//        rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息唯一ID
//        CorrelationData correlationData = new CorrelationData();
        rabbitTemplate.convertAndSend("test-Queue", "发工资了~~~~"+ new Random().nextInt());
    }
    @GetMapping("mq2")
    @ResponseBody
    public void sendOrder2() throws Exception {
        // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
//        rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息唯一ID
//        CorrelationData correlationData = new CorrelationData();
//        rabbitTemplate.convertAndSend("test-Queue", "发工资了~~~~");
        rabbitTemplate.convertAndSend("test.1", "发工资了~~~~"+ new Random().nextInt());
    }
}
