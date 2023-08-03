package com.ecole221.client.paiement.controller;


import com.ecole221.client.paiement.service.ClientService;
import com.ecole221.common.service.dto.ClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity getAllClients() {
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveClient(@RequestBody ClientDTO clientDTO) {
        return new ResponseEntity(clientService.saveClient(clientDTO), HttpStatus.CREATED);
    }
}