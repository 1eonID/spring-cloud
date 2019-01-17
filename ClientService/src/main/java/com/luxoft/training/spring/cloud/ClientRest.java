package com.luxoft.training.spring.cloud;

import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientRest {
    @Autowired
    ClientDAO clientDAO;
    @Autowired
    ClientRepository clientRepository;

    @RequestMapping("/create")
    public void createClient(@RequestParam String name) {
        clientDAO.create(name);
    }

    @RequestMapping("/update/{id}")
    public HttpStatus updateClient(@PathVariable Integer id,
                                  @RequestParam String name) {
        if (clientDAO.update(id, name)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @RequestMapping("/get/{id}")
    public ResponseEntity<?> getClient(@PathVariable Integer id) {

        Optional<ClientEntity> clientEntity = Optional.ofNullable(clientRepository.findOne(id));
        return clientEntity.map(Object.class::cast)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest()
                        .body("There is no client with ID = " + id));
    }

    @RequestMapping("/get")
    public ResponseEntity<?> getAllClient() {

        List<ClientEntity> list = clientRepository.findAll();
        return ResponseEntity.ok(list);
    }


}
