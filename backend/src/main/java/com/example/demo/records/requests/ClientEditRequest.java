package com.example.demo.records.requests;

public record ClientEditRequest(
        String firstName,
        String lastName,
        String login
) {
}
