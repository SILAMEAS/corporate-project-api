package com.corporate.project.laza_api.controller;

import com.corporate.project.laza_api.exception.DuplicateException;
import com.corporate.project.laza_api.jwt.JwtService;
import com.corporate.project.laza_api.model.apiresponse.ApiResponse;
import com.corporate.project.laza_api.model.request.AppUserRequest;
import com.corporate.project.laza_api.model.request.AuthPasswordChangeRequest;
import com.corporate.project.laza_api.model.request.AuthRequest;
import com.corporate.project.laza_api.model.response.AuthResponse;
import com.corporate.project.laza_api.service.AppUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthRestController {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    private void authenticate(String username, String password) throws Exception {
        try {
            UserDetails userApp = appUserService.loadUserByUsername(username);
            if (userApp == null) {
                throw new BadRequestException("Wrong Email");
            }
            if (!passwordEncoder.matches(password, userApp.getPassword())) {
                throw new BadRequestException("Wrong Password");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        authenticate(authRequest.getEmail(), authRequest.getPassword());
        final UserDetails userDetails = appUserService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtService.generateToken(userDetails);
        if (token != null) {
            var userLoginResponse = appUserService.userAfterLogin(authRequest.getEmail());
            userLoginResponse.setToken(token);
            return new ApiResponse<AuthResponse>("User Login Successfully", userLoginResponse, 200);
        }
        return new ApiResponse<>("Cannot Found This User", null, 404);
    }


    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody AppUserRequest appUserRequest) {
        return new ApiResponse<>("User Registered Successfully", appUserService.register(appUserRequest), 200);
    }

    @DeleteMapping("/remove/{userId}")
    public ApiResponse<?> removeUser( @PathVariable  Integer userId) {
        var deletedUser = appUserService.getUserById(userId);
        if (deletedUser == null) {
            return new ApiResponse<>("User Not Found", null, 404);
        }
        appUserService.deleteUserById(userId);
        return new ApiResponse<>("User Removed Successfully", deletedUser, 200);
    }

    @GetMapping("/getUser/{userId}")
    public ApiResponse<?> getUserById(@PathVariable Integer userId) {
        var user = appUserService.getUserById(userId);
        return new ApiResponse<>("User Found", user, 200);
    }

    @PatchMapping("/change/password/{userId}")
    public ApiResponse<?> changePasswordById(@PathVariable Integer userId, @RequestBody AuthPasswordChangeRequest authPasswordChangeRequest) {
        if (authPasswordChangeRequest.getNewPassword() == authPasswordChangeRequest.getOldPassword()) {
            throw new DuplicateException("Password cannot be the same");
        }
        var user = appUserService.getUserById(userId);
        appUserService.changePassword(userId, authPasswordChangeRequest);
        return new ApiResponse<>("User Password Changed Successfully", user, 200);
    }


}
