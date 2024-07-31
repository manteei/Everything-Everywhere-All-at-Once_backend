package com.example.db.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUser {
    public ResponseUser(String role, String message) {
        this.role = role;
        this.message = message;
    }

    public ResponseUser(String role, String message, String accessToken) {
        this.role = role;
        this.message = message;
        this.accessToken = accessToken;
    }

    private String role;
    private String message;
    private String accessToken;
}
