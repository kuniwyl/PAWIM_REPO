package com.example.demo.records.responses;

import com.example.demo.models.Basket;

import java.util.List;

public class BasketResponse {

    public record BasketResponseRecord(
            long id,
            List<Long> products
    ){};

    public BasketResponseRecord create(Basket basket){
        return new BasketResponseRecord(
            basket.getId(),
            basket.getProdukts().stream().map(product -> product.getId()).toList()
        );
    }
}
