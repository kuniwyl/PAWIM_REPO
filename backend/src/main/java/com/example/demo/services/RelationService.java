package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.records.requests.CommentCreateRequest;
import com.example.demo.records.requests.ImageCreateRequest;
import com.example.demo.records.responses.BasketResponse;
import com.example.demo.records.responses.ClientResponse;
import com.example.demo.records.responses.CommentResponse;
import com.example.demo.records.responses.ImageResponse;
import com.example.demo.repositories.*;
import org.springframework.stereotype.Service;

@Service
public class RelationService {

    private ClientRepository clientRepository;

    private BasketRepository basketRepository;

    private ProductRepository productRepository;

    private ImageRepository imageRepository;

    private CommentRepository commentRepository;

    private ClientResponse clientResponse = new ClientResponse();
    private BasketResponse basketResponse = new BasketResponse();
    private CommentResponse commentResponse = new CommentResponse();
    private ImageResponse imageResponse = new ImageResponse();

    public RelationService(
            ClientRepository clientRepository,
            BasketRepository basketRepository,
            ProductRepository productRepository,
            ImageRepository imageRepository,
            CommentRepository commentRepository

    ) {
        this.clientRepository = clientRepository;
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.commentRepository = commentRepository;
    }

    public ClientResponse.ClientResponseRecord createBasket(Long client_id){
        Client client = clientRepository.findById(client_id).get();
        Basket basket = new Basket(client);
        client.setBasket(basket);
        basketRepository.save(basket);
        return clientResponse.create(client);
    }

    public BasketResponse.BasketResponseRecord addProductToBasket(Long basket_id, Long product_id){
        Product product = productRepository.findById(product_id).get();
        Basket basket = basketRepository.findById(basket_id).get();
        basket.addProduct(product);
        product.addBastek(basket);
        return basketResponse.create(basketRepository.save(basket));
    }

    public BasketResponse.BasketResponseRecord delProductFromBasket(Long basket_id, Long product_id){
        Product product = productRepository.findById(product_id).get();
        Basket basket = basketRepository.findById(basket_id).get();
        basket.delProduct(product);
        product.delBasket(basket);
        return basketResponse.create(basketRepository.save(basket));
    }

    public ImageResponse.ImageResponseRecord addImageToProduct(Long product_id, ImageCreateRequest imageCreateRequest){
        Product product = productRepository.findById(product_id).get();
        Image image = new Image(
                imageCreateRequest.location(),
                imageCreateRequest.name(),
                product
        );
        product.addImage(image);
        return imageResponse.create(imageRepository.save(image));
    }

    public CommentResponse.CommentResponseRecord addCommentToProduct(Long client_id, Long product_id, CommentCreateRequest commentCreateRequest){
        Product product = productRepository.findById(product_id).get();
        Client client = clientRepository.findById(client_id).get();
        Comment comment = new Comment(
                commentCreateRequest.content(),
                client,
                product
        );
        product.addComment(comment);
        return commentResponse.create(commentRepository.save(comment));
    }

}
