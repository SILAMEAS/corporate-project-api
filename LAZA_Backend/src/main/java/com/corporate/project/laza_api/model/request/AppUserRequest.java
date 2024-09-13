package com.corporate.project.laza_api.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest extends AuthRequest{
    @NotBlank(message = "fullName can't be blank")
    @Schema(name = "fullName",example = "laza",defaultValue = "laza me")
    private String fullName;
    private List<String> roles;
}
