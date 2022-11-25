package com.example.demo;

import com.example.demo.models.Basket;
import com.example.demo.records.responses.BasketResponse;
import com.example.demo.repositories.BasketRepository;
import com.example.demo.services.BasketService;
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
public class BasketTests {
    @Mock
    private BasketRepository repository;

    @InjectMocks
    private BasketService controller;

    private BasketResponse basketResponse = new BasketResponse();

    @Test
    public void get_all_Koszyks_from_repository_data_in_repo(){
        // arange
        repository = mock(BasketRepository.class);
        controller = new BasketService(repository);
        List<Basket> baskets = new ArrayList<>();
        baskets.add(new Basket());
        baskets.add(new Basket());
        when(repository.findAll()).thenReturn(baskets);
        List<BasketResponse.BasketResponseRecord> responseRecord =
                baskets.stream().map(basket -> basketResponse.create(basket)).toList();

        // act
        List<BasketResponse.BasketResponseRecord> result = controller.getAll();

        // assert
        Assert.assertEquals(responseRecord, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_all_Koszyks_from_repository_without_data(){
        // arange
        repository = mock(BasketRepository.class);
        controller = new BasketService(repository);
        List<Basket> baskets = new ArrayList<>();
        when(repository.findAll()).thenReturn(baskets);
        List<BasketResponse.BasketResponseRecord> responseRecord =
                baskets.stream().map(basket -> basketResponse.create(basket)).toList();

        // act
        List<BasketResponse.BasketResponseRecord> result = controller.getAll();

        // assert
        Assert.assertEquals(responseRecord, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void get_one_Koszyk_from_repository(){
        // arange
        repository = mock(BasketRepository.class);
        controller = new BasketService(repository);
        Basket basket = new Basket();
        when(repository.findById(0L)).thenReturn(Optional.of(basket));
        BasketResponse.BasketResponseRecord expected = basketResponse.create(basket);

        // act
        BasketResponse.BasketResponseRecord result = controller.getOne(0L);

        // assert
        Assert.assertEquals(expected, result);
        verify(repository, times(1)).findById(0L);
    }
}
