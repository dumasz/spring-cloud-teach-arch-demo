package com.dumas.scta.service.call.ribbon.consumer.custom.config.yml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author dumas
 * @desc TODO
 * @date 2022/05/03 23:57
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RibbonConsumerCustomConfigYmlApplication {
    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerCustomConfigYmlApplication.class, args);
    }
}
