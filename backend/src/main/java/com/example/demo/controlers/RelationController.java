package com.example.demo.controlers;

import com.example.demo.records.requests.CommentCreateRequest;
import com.example.demo.records.requests.ImageCreateRequest;
import com.example.demo.records.responses.BasketResponse;
import com.example.demo.records.responses.ClientResponse;
import com.example.demo.records.responses.CommentResponse;
import com.example.demo.records.responses.ImageResponse;
import com.example.demo.services.RelationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/relation")
public class RelationController {

    private RelationService relationService;

    public RelationController(RelationService relationService){
        this.relationService = relationService;
    }

    @PostMapping("/add/client/{client_id}/basket")
    public ClientResponse.ClientResponseRecord createBasket(@PathVariable Long client_id){
        return relationService.createBasket(client_id);
    }

    @PostMapping("/add/basket/{basket_id}/product/{product_id}")
    public BasketResponse.BasketResponseRecord addProduct(@PathVariable Long basket_id, @PathVariable Long product_id){
        return relationService.addProductToBasket(basket_id, product_id);
    }

    @DeleteMapping("/delete/basket/{basket_id}/product/{product_id}")
    public BasketResponse.BasketResponseRecord delProduct(@PathVariable Long basket_id, @PathVariable Long product_id){
        return relationService.delProductFromBasket(basket_id, product_id);
    }

    @PostMapping("/add/product/{product_id}/image")
    public ImageResponse.ImageResponseRecord addImage(@PathVariable Long product_id, @RequestBody ImageCreateRequest imageCreateRequest){
        return relationService.addImageToProduct(product_id, imageCreateRequest);
    }

    @PostMapping("/add/client/{client_id}/product/{product_id}/comment")
    public CommentResponse.CommentResponseRecord addComment(@PathVariable Long product_id, @PathVariable Long client_id,@RequestBody CommentCreateRequest commentCreateRequest){
        return relationService.addCommentToProduct(client_id, product_id, commentCreateRequest);
    }

}
