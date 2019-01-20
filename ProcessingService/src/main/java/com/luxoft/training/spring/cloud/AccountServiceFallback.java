package com.luxoft.training.spring.cloud;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Component
public class AccountServiceFallback implements AccountServiceClient {
    @Override
    public boolean checkout(@PathVariable("id") Integer id,
                            @RequestParam BigDecimal sum) {
        return false;
    }
}
