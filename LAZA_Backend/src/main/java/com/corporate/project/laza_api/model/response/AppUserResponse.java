package com.corporate.project.laza_api.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AppUserResponse {
    private Integer id;
    private String fullName;
    private String email;
    private List<String> roles;  // String if you're fetching role names

    // Constructor matching the query result
    public AppUserResponse(Integer id, String fullName, String email, List<String> roles) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.roles = roles;
    }
}

