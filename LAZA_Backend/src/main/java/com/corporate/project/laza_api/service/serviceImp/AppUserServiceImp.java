package com.corporate.project.laza_api.service.serviceImp;

import com.corporate.project.laza_api.exception.NotFoundException;
import com.corporate.project.laza_api.model.apiresponse.ApiResponse;
import com.corporate.project.laza_api.model.entity.AppUser;
import com.corporate.project.laza_api.model.entity.Role;
import com.corporate.project.laza_api.model.request.AppUserRequest;
import com.corporate.project.laza_api.model.request.AuthPasswordChangeRequest;
import com.corporate.project.laza_api.model.response.AppUserResponse;
import com.corporate.project.laza_api.model.response.AuthResponse;
import com.corporate.project.laza_api.repository.AppUserRepository;
import com.corporate.project.laza_api.repository.RoleRepository;
import com.corporate.project.laza_api.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppUserServiceImp implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public AppUserResponse register(AppUserRequest appUserRequest) {
        AppUser user = new AppUser();
        user.setFullName(appUserRequest.getFullName());
        user.setEmail(appUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));

        // Convert role names to Role entities
        List<String> roleNames = appUserRequest.getRoles(); // Get roles from request
        List<Role> roles = roleRepository.findAllByNameIn(roleNames);

        // Set roles for the user
        user.setRoles(roles);

        // Save user
        AppUser savedUser = appUserRepository.save(user);

        // Prepare response
        List<String> roleNamesResponse = savedUser.getRoles().stream()
                .map(Role::getName) // Ensure Role class has getName() method
                .collect(Collectors.toList());

        return new AppUserResponse(savedUser.getId(), savedUser.getFullName(), savedUser.getEmail(), roleNamesResponse);
    }



    @Override
    public AppUserResponse getUserById(Integer userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName) // Ensure Role class has getName() method
                .collect(Collectors.toList());

        return new AppUserResponse(user.getId(), user.getFullName(), user.getEmail(), roleNames);
    }


    @Override
    public void deleteUserById(Integer userId) {
        appUserRepository.deleteById(userId);
    }

    @Override
    public ApiResponse changePassword(Integer userId, AuthPasswordChangeRequest authPasswordChangeRequest) {
//        AppUser user = getUserById(userId);
//        if (user != null) {
//            user.setPassword(passwordEncoder.encode(authPasswordChangeRequest.getNewPassword()));
//            appUserRepository.save(user);
//        }
        return new ApiResponse<>("not yet implement",null,40033);
    }

    @Override
    public AuthResponse userAfterLogin(String email) {
        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert List<Role> to List<String>
        List<String> roleNames = user.getRoles().stream()
                .map(Role::getName) // Ensure Role class has getName() method
                .collect(Collectors.toList());

        // Return the customized AuthResponse
        return new AuthResponse(null, user.getFullName(), user.getEmail(), roleNames);
    }


}


