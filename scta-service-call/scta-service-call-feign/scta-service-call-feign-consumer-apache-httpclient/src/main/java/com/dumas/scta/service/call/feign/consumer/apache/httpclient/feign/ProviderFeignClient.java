package com.dumas.scta.service.call.feign.consumer.apache.httpclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author dumas
 * @desc TODO
 * @date 2022/05/04 01:07
 */
@FeignClient(name = "scta-service-call-feign-provider")
public interface ProviderFeignClient {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);
}
