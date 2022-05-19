package com.dumas.mq.stream.kafka.transaction;

import com.dumas.mq.stream.kafka.transaction.message.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author dumas
 * @date 2022/05/19 10:48 AM
 */
@SpringBootApplication
@EnableBinding(MySource.class)
public class KafkaProducerTransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerTransactionApplication.class, args);
    }
}
