package com.luxoft.training.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountRest {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/create")
    public void createClient(@RequestParam Integer id) {
        accountDAO.create(id);
    }

    @RequestMapping("/fund")
    public void fundClient(@RequestParam Integer id,
                           @RequestParam BigDecimal sum) {

        accountDAO.addBalance(id, sum.abs());
    }

    @RequestMapping("/checkout")
    public void checkoutClient(@RequestParam Integer id,
                           @RequestParam BigDecimal sum) {

        accountDAO.addBalance(id, sum.abs().negate());
    }

    @RequestMapping("/get/{clientId}")
    public ResponseEntity<?> getClient(@PathVariable Integer clientId) {

        Optional<List<AccountEntity>> clientEntity = Optional.ofNullable(accountRepository.findByClientId(clientId));
        return clientEntity.map(Object.class::cast)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest()
                        .body("There is no account with ID = " + clientId));
    }
}
