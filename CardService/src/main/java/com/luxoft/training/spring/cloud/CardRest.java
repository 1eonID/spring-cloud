package com.luxoft.training.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardRest {
    @Autowired
    private CardNumberGenerator cardNumberGenerator;

    @RequestMapping("create")
    public String createCard() {
        return cardNumberGenerator.generate();
    }

}
