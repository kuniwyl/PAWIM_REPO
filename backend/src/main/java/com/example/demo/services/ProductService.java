package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.records.requests.ProductCreateRequest;
import com.example.demo.records.responses.ProductResponse;
import com.example.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    private ProductResponse productResponse = new ProductResponse();

    public ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }

    public List<ProductResponse.ProductResponseRecord> getALl(){
        List<Product> products = repository.findAll();
        return products.stream().map(product -> productResponse.create(product)).toList();
    }

    public ProductResponse.ProductResponseRecord getOne(long id){
        return productResponse.create(repository.findById(id).get());
    }

    public ProductResponse.ProductResponseRecord create(ProductCreateRequest productCreateRequest){
        Product product = new Product(
                productCreateRequest.name(),
                productCreateRequest.price(),
                productCreateRequest.description()
        );
        repository.save(product);
        return productResponse.create(product);
    }

    public ProductResponse.ProductResponseRecord edit(long id, ProductCreateRequest productCreateRequest){
        Product product = null;
        if(repository.existsById(id)) {
            product = repository.findById(id).get();
            product.setName(productCreateRequest.name());
            product.setPrice(productCreateRequest.price());
            product.setDescription(productCreateRequest.description());
            repository.save(product);
        }
        return productResponse.create(product);
    }

    public void delete(long id){
        repository.deleteById(id);
    }

}
