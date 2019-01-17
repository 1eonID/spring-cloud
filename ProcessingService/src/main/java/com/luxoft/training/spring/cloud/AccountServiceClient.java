package com.luxoft.training.spring.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("AccountService")
public interface AccountServiceClient {
    @RequestMapping("/checkout/{id}")
    AccountService getAccount(@PathVariable("id") Integer id);
}
