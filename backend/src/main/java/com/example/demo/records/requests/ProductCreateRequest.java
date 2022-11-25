package com.example.demo.records.requests;

public record ProductCreateRequest(
        String name,
        int price,
        String description
) {
}
