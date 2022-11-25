package com.example.demo.controlers;

import com.example.demo.models.Client;
import com.example.demo.records.requests.ClientEditRequest;
import com.example.demo.records.requests.ClientRegisterRequest;
import com.example.demo.records.responses.ClientResponse;
import com.example.demo.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/show")
    public List<ClientResponse.ClientResponseRecord> all(){
        return clientService.getALl();
    }

    @GetMapping("/show/{id}")
    public ClientResponse.ClientResponseRecord one(@PathVariable long id){
        return clientService.getOne(id);
    }

    @PostMapping("/create")
    public ClientResponse.ClientResponseRecord create(@RequestBody ClientRegisterRequest client){
        return clientService.create(client);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        clientService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ClientResponse.ClientResponseRecord update(@PathVariable long id, @RequestBody ClientEditRequest newClient){
        return clientService.edit(id, newClient);
    }

}
