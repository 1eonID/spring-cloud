package com.luxoft.training.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessingRest {
    @Autowired
    ProcessingRepository processingRepository;
    @Autowired
    AccountServiceClient accountServiceClient;
    @Autowired
    CardServiceClient cardServiceClient;

    @RequestMapping("/issue/{accountId}")
    public void getAndSaveCard(@RequestParam("accountId") Integer accountId) {

    }
}
