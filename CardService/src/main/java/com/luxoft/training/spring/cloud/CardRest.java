package com.luxoft.training.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardRest {
    @Autowired
    CardNumberGenerator cardNumberGenerator;

    @RequestMapping("/create")
    public ResponseEntity<?> createCard() {
        return ResponseEntity.ok(cardNumberGenerator.generate());
    }

}
