package com.example.demo.records.requests;

public record ClientRegisterRequest(
        String firstName,
        String lastName,
        String login,
        String password
) { }
