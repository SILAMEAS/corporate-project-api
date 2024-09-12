package com.corporate.project.laza_api.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private String email;
    private String fullName;
    private List<String> roles;  // List<String> to store role names

}
