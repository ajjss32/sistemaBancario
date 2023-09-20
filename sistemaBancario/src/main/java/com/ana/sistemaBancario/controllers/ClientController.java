package com.ana.sistemaBancario.controllers;

import com.ana.sistemaBancario.dtos.ClientRequest;
import com.ana.sistemaBancario.dtos.ClientResponse;
import com.ana.sistemaBancario.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody @Valid ClientRequest clientRequest){
        ClientResponse clientResponse = clientService.createClient(clientRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
    }
    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients(){
        List<ClientResponse> allClients = clientService.getAllClients();
        return ResponseEntity.ok(allClients);
    }
    @GetMapping("{idclient}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable(value = "idclient") Long idCliente){
        ClientResponse client = clientService.findClientById(idCliente);
        return ResponseEntity.ok(client);
    }
    @DeleteMapping("{idclient}")
    public void deleteClient(@PathVariable(value = "idclient") Long idCliente){
        clientService.deleteClient(idCliente);
    }
}
