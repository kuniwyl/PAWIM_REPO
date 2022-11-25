package com.example.demo.records.responses;

import com.example.demo.models.Product;

import java.util.List;

public class ProductResponse {

    public record ProductResponseRecord (
            long id,
            String name,
            int price,
            String description,
            List<Long> comments,
            List<Long> images
    ){};

    public ProductResponse(){};

    public ProductResponseRecord create(Product product){
        return new ProductResponseRecord(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getComments().stream().map(comment -> comment.getId()).toList(),
                product.getImages().stream().map(image -> image.getId()).toList()
        );
    }
}
