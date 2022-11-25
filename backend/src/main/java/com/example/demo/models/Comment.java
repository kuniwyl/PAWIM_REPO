package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "comment")
public class Comment {

    private @Id @GeneratedValue long id;
    private String text;
    private String date;

    // Relacje
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client = null;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product = null;

    public Comment() {
    }

    public Comment(String value, Client client, Product product) {
        this.text = value;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
        this.client = client;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        this.text = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Client getKlient() {
        return client;
    }

    public void setKlient(Client client) {
        this.client = client;
    }

    public Product getProdukt() {
        return product;
    }

    public void setProdukt(Product product) {
        this.product = product;
    }
}
