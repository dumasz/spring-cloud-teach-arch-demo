package com.dumas.scta.mq.stream.rocketmq.orderly.controller;

import com.dumas.scta.mq.stream.rocketmq.orderly.message.Demo01Message;
import com.dumas.scta.mq.stream.rocketmq.orderly.message.MySource;
import org.apache.rocketmq.common.message.MessageConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author dumas
 * @desc TODO
 * @date 2022/05/04 21:52
 */
@RestController
@RequestMapping("/demo01")
public class Demo01Controller {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MySource mySource;

    @GetMapping("/send")
    public boolean send() {
        // <1> 创建 Message
        Demo01Message message = new Demo01Message()
                .setId(new Random().nextInt());
        // <2> 创建 Spring Message 对象
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .build();
        // <3> 发送消息
        return mySource.demo01Output().send(springMessage);
    }

    /**
     * 定时消息/延迟消息
     *
     * @return
     */
    @GetMapping("send_delay")
    public boolean sendDelay() {
        // 创建 Message
        Demo01Message message = new Demo01Message()
                .setId(new Random().nextInt());
        // 创建 Spring Message 对象 设置延迟级别为 3，10 秒后消费
        Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                .setHeader(MessageConst.PROPERTY_DELAY_TIME_LEVEL, "3")
                .build();
        // 发送消息
        boolean sendResult = mySource.demo01Output().send(springMessage);
        logger.info("[sendDelay][发送消息完成，结果 = {}]", sendResult);
        return sendResult;
    }

    /**
     * 顺序消息
     * @return
     */
    @GetMapping("/send_orderly")
    public boolean sendOrderly() {
        // 发送 3 条相同 id 的消息
        int id = new Random().nextInt();
        for (int i = 0; i < 3; i++) {
            // 创建 Message
            Demo01Message message = new Demo01Message().setId(id);
            // 创建 Spring Message 对象
            Message<Demo01Message> springMessage = MessageBuilder.withPayload(message)
                    .build();
            // 发送消息
            mySource.demo01Output().send(springMessage);
        }
        return true;
    }
}
