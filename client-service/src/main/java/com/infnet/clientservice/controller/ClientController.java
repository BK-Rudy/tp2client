package com.infnet.clientservice.controller;

import com.infnet.clientservice.model.Client;
import com.infnet.clientservice.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        return client.isPresent() ? ResponseEntity.ok(client.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
