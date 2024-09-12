package com.corporate.project.laza_api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
    private String fullName;
    private String email;
    private String password;
    private List<String> roles;
}
