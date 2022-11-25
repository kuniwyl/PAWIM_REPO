package com.example.demo.controlers;

import com.example.demo.records.responses.BasketResponse;
import com.example.demo.services.BasketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService){
        this.basketService = basketService;
    }

    @GetMapping("/show")
    public List<BasketResponse.BasketResponseRecord> all(){
        return basketService.getAll();
    }

    @GetMapping("/show/{id}")
    public BasketResponse.BasketResponseRecord one(@PathVariable long id){
        return basketService.getOne(id);
    }
}
