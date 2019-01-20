package com.luxoft.training.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProcessingRest {
    @Autowired
    private ProcessingRepository processingRepository;
    @Autowired
    private AccountServiceClient accountServiceClient;
    @Autowired
    private CardServiceClient cardServiceClient;

    @RequestMapping("/issue/{accountId}")
    public String getAndSaveCard(@PathVariable Integer accountId) {
        if (cardServiceClient != null) {
            final String card = cardServiceClient.createCard();
            ProcessingEntity entity = new ProcessingEntity();
            entity.setCard(card);
            entity.setAccountId(accountId);
            processingRepository.save(entity);
            return card;
        } else {
            return "CARD_SERVICE_OT_AVAILABLE";
        }
    }

    @RequestMapping("/checkout/{card}")
    public boolean checkCard(@PathVariable("card") String card,
                             @RequestParam("sum") BigDecimal sum) {
        ProcessingEntity entity = processingRepository.findByCard(card);
        if (entity != null) {
            return accountServiceClient.checkout(entity.getAccountId(), sum);
        } else {
            return false;
        }
    }

    @RequestMapping("/get")
    public Map<Integer, String> getAccAndCardMap(@RequestParam("accountId") List<Integer> accList) {
        List<ProcessingEntity> entityList = processingRepository.findByAccountIdIn(accList);
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (ProcessingEntity entity : entityList) {
            map.put(entity.getAccountId(), entity.getCard());
        }
        return map;
    }
}
