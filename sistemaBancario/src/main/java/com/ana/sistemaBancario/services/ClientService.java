package com.ana.sistemaBancario.services;

import com.ana.sistemaBancario.dtos.ClientRequest;
import com.ana.sistemaBancario.dtos.ClientResponse;
import com.ana.sistemaBancario.models.Client;
import com.ana.sistemaBancario.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;
    public ClientResponse createClient(ClientRequest clientRequest){
        Client client = new Client();
        modelMapper.map(clientRequest,client);
        clientRepository.save(client);
        return modelMapper.map(client, ClientResponse.class);
    }
    public ClientResponse findClientById(Long idClient){
        Client client = clientRepository.findById(idClient).orElseThrow(()->new EntityNotFoundException("Cliente não encontrado"));
        return modelMapper.map(client, ClientResponse.class);
    }
    public List<ClientResponse> getAllClients(){
        return clientRepository.findAll().stream()
                .map(client -> modelMapper.map(client,ClientResponse.class)).toList();
    }
    public void deleteClient(Long idClient){
        Client client = clientRepository.findById(idClient).orElseThrow(()->new EntityNotFoundException("Cliente não encontrado"));
        clientRepository.delete(client);
    }
    public ClientResponse updateClient(ClientRequest clientRequest,Long idClient){
        Client client = clientRepository.findById(idClient).orElseThrow(()->new EntityNotFoundException("Cliente não encontrado"));
        client.setAddress(clientRequest.getAddress());
        client.setEmail(clientRequest.getEmail());
        return modelMapper.map(client,ClientResponse.class);
    }
    public Client findClient(Long idClient){
        return clientRepository.findById(idClient).orElseThrow(()->new EntityNotFoundException("Cliente não encontrado"));
    }
}
