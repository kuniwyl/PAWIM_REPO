package com.example.demo.controlers;

import com.example.demo.models.Product;
import com.example.demo.records.requests.ProductCreateRequest;
import com.example.demo.records.responses.ProductResponse;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/show")
    public List<ProductResponse.ProductResponseRecord> all(){
        return productService.getALl();
    }

    @GetMapping("/show/{id}")
    public ProductResponse.ProductResponseRecord one(@PathVariable long id){
        return productService.getOne(id);
    }

    @PostMapping("/create")
    public ProductResponse.ProductResponseRecord create(@RequestBody ProductCreateRequest productCreateRequest){
        return productService.create(productCreateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        productService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ProductResponse.ProductResponseRecord update(@PathVariable long id, @RequestBody ProductCreateRequest productCreateRequest){
        return productService.edit(id, productCreateRequest);
    }
}
