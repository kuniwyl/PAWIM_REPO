package com.example.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Column(name = "product_id")
    private @Id @GeneratedValue long id;
    private String name;
    private int price;
    private String description;

    // Relacje
    @ManyToMany
    @JoinTable(
            name = "product_basket",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "basket_id")
    )
    private List<Basket> baskets = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    public Product() {
    }

    public Product(String name, int value, String description) {
        this.name = name;
        this.price = value;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int value) {
        this.price = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<Basket> baskets) {
        this.baskets = baskets;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> zdjecies) {
        this.images = zdjecies;
    }

    public void addBastek(Basket basket) {
        this.baskets.add(basket);
    }

    public void delBasket(Basket basket) {
        this.baskets.remove(basket);
    }

    public void addImage(Image image) {
        this.images.add(image);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
