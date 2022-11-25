package com.example.demo.records.responses;

import com.example.demo.models.Client;

import java.util.List;

public class ClientResponse {

    public record ClientResponseRecord (
            long id,
            String first_name,
            String last_name,
            String login,
            long basket_id,
            List<Long> comments_id
    ){};

    public ClientResponse(){
    }

    public ClientResponseRecord create(Client client){
        return new ClientResponseRecord(
                client.getId(),
                client.getFirst_name(),
                client.getLast_name(),
                client.getLogin(),
                client.getBasket() == null ? -1 : client.getBasket().getId(),
                client.getComments().stream().map(comment -> comment.getId()).toList()
        );
    }
}
