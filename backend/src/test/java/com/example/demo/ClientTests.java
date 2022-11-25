package com.example.demo;

import com.example.demo.models.Client;
import com.example.demo.records.requests.ClientEditRequest;
import com.example.demo.records.requests.ClientRegisterRequest;
import com.example.demo.records.responses.BasketResponse;
import com.example.demo.records.responses.ClientResponse;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.ClientService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientTests {

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientService controller;

    private ClientResponse clientResponse = new ClientResponse();

    @Test
    public void get_all_Klients_from_repository_data_in_repo(){
        // arange
        repository = mock(ClientRepository.class);
        controller = new ClientService(repository);
        List<Client> clients = new ArrayList<>();
        clients.add(new Client());
        clients.add(new Client());
        when(repository.findAll()).thenReturn(clients);
        List<ClientResponse.ClientResponseRecord> responseRecord =
                clients.stream().map(basket -> clientResponse.create(basket)).toList();

        // act
        List<ClientResponse.ClientResponseRecord> result = controller.getALl();

        // assert
        Assert.assertEquals(responseRecord, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_all_Klients_from_repository_without_data(){
        // arange
        repository = mock(ClientRepository.class);
        controller = new ClientService(repository);
        List<Client> clients = new ArrayList<>();
        when(repository.findAll()).thenReturn(clients);
        List<ClientResponse.ClientResponseRecord> responseRecord =
                clients.stream().map(basket -> clientResponse.create(basket)).toList();

        // act
        List<ClientResponse.ClientResponseRecord> result = controller.getALl();

        // assert
        Assert.assertEquals(responseRecord, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_one_Klient_from_repository(){
        // arange
        repository = mock(ClientRepository.class);
        controller = new ClientService(repository);
        Client client = new Client();
        when(repository.findById(0L)).thenReturn(Optional.of(client));
        ClientResponse.ClientResponseRecord expected = clientResponse.create(client);

        // act
        ClientResponse.ClientResponseRecord result = controller.getOne(0L);

        // assert
        Assert.assertEquals(expected, result);
        verify(repository, times(1)).findById(0L);
    }

    @Test
    public void create_new_klient() {
        // arange
        repository = mock(ClientRepository.class);
        controller = new ClientService(repository);
        Client client = new Client();
        when(repository.save(client)).thenReturn(client);
        ClientResponse.ClientResponseRecord expected = clientResponse.create(client);

        // act
        ClientResponse.ClientResponseRecord result = controller.create(new ClientRegisterRequest(
                client.getFirst_name(),
                client.getLast_name(),
                client.getLogin(),
                client.getPassword()
        ));

        // assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void delete_klient() {
        // arange
        repository = mock(ClientRepository.class);
        controller = new ClientService(repository);
        Client client = new Client();
        client.setId(0L);
        when(repository.findById(client.getId())).thenReturn(Optional.of(client));

        // act
        controller.delete(client.getId());

        // assert
        verify(repository, times(1)).deleteById(client.getId());
    }

    @Test
    public void delete_not_existing_id() {
        // arange
        repository = mock(ClientRepository.class);
        controller = new ClientService(repository);
        when(repository.findById(1L)).thenReturn(null);

        // act
        controller.delete(1L);

        // assert
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void put_klient_on_existing_id() {
        // arange
        repository = mock(ClientRepository.class);
        controller = new ClientService(repository);
        Client client = new Client();
        client.setId(0L);
        when(repository.existsById(0L)).thenReturn(true);
        when(repository.findById(0L)).thenReturn(Optional.of(client));

        // act
        ClientResponse.ClientResponseRecord result = controller.edit(0L, new ClientEditRequest(
                client.getFirst_name(),
                client.getLast_name(),
                client.getLogin()
        ));

        // assert
        Assert.assertEquals(client.getId(), result.id());
        verify(repository, times(1)).save(client);
        verify(repository, times(1)).findById(client.getId());
    }

}
