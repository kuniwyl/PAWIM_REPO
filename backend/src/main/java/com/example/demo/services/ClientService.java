package com.example.demo.services;

import com.example.demo.models.Client;
import com.example.demo.records.requests.ClientEditRequest;
import com.example.demo.records.requests.ClientRegisterRequest;
import com.example.demo.records.responses.ClientResponse;
import com.example.demo.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository repository;

    private ClientResponse clientResponse = new ClientResponse();

    public ClientService(ClientRepository clientRepository){
        this.repository = clientRepository;
    }

    public List<ClientResponse.ClientResponseRecord> getALl(){
        List<Client> clients = repository.findAll();
        return clients.stream().map(client -> clientResponse.create(client)).toList();
    }

    public ClientResponse.ClientResponseRecord getOne(long id){
        return clientResponse.create(repository.findById(id).get());
    }

    public ClientResponse.ClientResponseRecord create(ClientRegisterRequest clientRegisterRequest){
        Client client = new Client(
                clientRegisterRequest.firstName(),
                clientRegisterRequest.lastName(),
                clientRegisterRequest.login(),
                clientRegisterRequest.password()
        );
        repository.save(client);
        return clientResponse.create(client);
    }

    public ClientResponse.ClientResponseRecord edit(long id, ClientEditRequest clientEditRequest){
        Client client = null;
        if(repository.existsById(id)) {
            client = repository.findById(id).get();
            client.setFirst_name(clientEditRequest.firstName());
            client.setLast_name(clientEditRequest.lastName());
            client.setLogin(clientEditRequest.login());
            repository.save(client);
        }
        return clientResponse.create(client);
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}
