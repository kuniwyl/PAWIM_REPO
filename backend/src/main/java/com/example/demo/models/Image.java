package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    private @Id @GeneratedValue long id;
    private String location;
    private String name;

    // Relacje
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product = null;

    public Image() {
    }

    public Image(String location, String name, Product product) {
        this.location = location;
        this.name = name;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProdukt() {
        return product;
    }

    public void setProdukt(Product product) {
        this.product = product;
    }
}
