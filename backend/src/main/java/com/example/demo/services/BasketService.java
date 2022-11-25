package com.example.demo.services;

import com.example.demo.models.Basket;
import com.example.demo.records.responses.BasketResponse;
import com.example.demo.repositories.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    private BasketRepository repository;

    private BasketResponse basketResponse = new BasketResponse();

    public BasketService(BasketRepository repository){
        this.repository = repository;
    }

    public List<BasketResponse.BasketResponseRecord> getAll(){
        List<Basket> baskets = repository.findAll();
        return baskets.stream().map(basket -> basketResponse.create(basket)).toList();
    }

    public BasketResponse.BasketResponseRecord getOne(long id){
        return basketResponse.create(repository.findById(id).get());
    }
}
