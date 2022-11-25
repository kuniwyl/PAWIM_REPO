package com.example.demo;

import com.example.demo.models.Product;
import com.example.demo.records.requests.ProductCreateRequest;
import com.example.demo.records.responses.ProductResponse;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
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
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class ProductTests {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService controller;

    private ProductResponse productResponse = new ProductResponse();

    @Test
    public void get_all_Produkts_from_repository_data_in_repo(){
        // arange
        repository = mock(ProductRepository.class);
        controller = new ProductService(repository);
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        when(repository.findAll()).thenReturn(products);
        List<ProductResponse.ProductResponseRecord> productResponseRecords =
                products.stream().map(product -> productResponse.create(product)).toList();

        // act
        List<ProductResponse.ProductResponseRecord> result = controller.getALl();

        // assert
        Assert.assertEquals(productResponseRecords, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_all_Produkts_from_repository_without_data(){
        // arange
        repository = mock(ProductRepository.class);
        controller = new ProductService(repository);
        List<Product> products = new ArrayList<>();
        when(repository.findAll()).thenReturn(products);
        List<ProductResponse.ProductResponseRecord> productResponseRecords =
                products.stream().map(product -> productResponse.create(product)).toList();

        // act
        List<ProductResponse.ProductResponseRecord> result = controller.getALl();

        // assert
        Assert.assertEquals(productResponseRecords, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_one_Produkt_from_repository(){
        // arange
        repository = mock(ProductRepository.class);
        controller = new ProductService(repository);
        Product product = new Product();
        when(repository.findById(0L)).thenReturn(Optional.of(product));
        ProductResponse.ProductResponseRecord expected = productResponse.create(product);

        // act
        ProductResponse.ProductResponseRecord result = controller.getOne(0L);

        // assert
        Assert.assertEquals(expected, result);
        verify(repository, times(1)).findById(0L);
    }

    @Test
    public void create_new_produkt() {
        // arange
        repository = mock(ProductRepository.class);
        controller = new ProductService(repository);
        Product product = new Product();
        when(repository.save(product)).thenReturn(product);
        ProductResponse.ProductResponseRecord expected = productResponse.create(product);

        // act
        ProductResponse.ProductResponseRecord result = controller.create(new ProductCreateRequest(
                product.getName(),
                product.getPrice(),
                product.getDescription()
        ));

        // assert
        Assert.assertEquals(expected, result);
    }

    @Test
    public void delete_produkt() {
        // arange
        repository = mock(ProductRepository.class);
        controller = new ProductService(repository);
        Product product = new Product();
        product.setId(0L);
        when(repository.findById(product.getId())).thenReturn(Optional.of(product));

        // act
        controller.delete(product.getId());

        // assert
        verify(repository, times(1)).deleteById(product.getId());
    }

    @Test
    public void delete_not_existing_id() {
        // arange
        repository = mock(ProductRepository.class);
        controller = new ProductService(repository);
        when(repository.findById(1L)).thenReturn(null);

        // act
        controller.delete(1L);

        // assert
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void put_produkt_on_existing_id() {
        // arange
        repository = mock(ProductRepository.class);
        controller = new ProductService(repository);
        Product product = new Product();
        product.setId(0L);
        when(repository.existsById(0L)).thenReturn(true);
        when(repository.findById(product.getId())).thenReturn(Optional.of(product));
        ProductResponse.ProductResponseRecord expected = productResponse.create(product);

        // act
        ProductResponse.ProductResponseRecord result = controller.edit(0L, new ProductCreateRequest(
                product.getName(),
                product.getPrice(),
                product.getDescription()
        ));

        // assert
        Assert.assertEquals(expected, result);
        verify(repository, times(1)).save(product);
        verify(repository, times(1)).findById(product.getId());
    }
}
