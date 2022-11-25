package com.example.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {

    @Column(name = "basket_id")
    private @Id @GeneratedValue long id;

    // Relacje
    @OneToOne(mappedBy = "basket")
    private Client client;

    @ManyToMany(mappedBy = "baskets")
    private List<Product> products = new ArrayList<>();

    public Basket() {
    }

    public Basket(Client client) {
        this.client = client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getKlient() {
        return client;
    }

    public void setKlient(Client client) {
        this.client = client;
    }

    public List<Product> getProdukts() {
        return products;
    }

    public void setProdukts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){ this.products.add(product); }

    public void delProduct(Product product){ this.products.remove(product); }
}