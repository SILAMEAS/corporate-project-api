package com.corporate.project.laza_api.service;


import com.corporate.project.laza_api.model.apiresponse.ApiResponse;
import com.corporate.project.laza_api.model.entity.AppUser;
import com.corporate.project.laza_api.model.request.AppUserRequest;
import com.corporate.project.laza_api.model.request.AuthPasswordChangeRequest;
import com.corporate.project.laza_api.model.response.AppUserResponse;
import com.corporate.project.laza_api.model.response.AuthResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUserResponse register(AppUserRequest appUserRequest);

    AppUserResponse getUserById(Integer userId);

    void deleteUserById(Integer userId);

    ApiResponse changePassword(Integer userId, AuthPasswordChangeRequest authPasswordChangeRequest);

    AuthResponse userAfterLogin(String email);
}

