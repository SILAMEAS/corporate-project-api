package com.corporate.project.laza_api.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is not valid")
    @Schema(name = "email",example = "admin@gmail.com",defaultValue = "sila@gmail.com")
    private String email;
    @NotEmpty(message = "password can't be blank")
    @Length(min = 8,message = "password must be at least 8'")
    @Schema(name = "password",example = "admin123",defaultValue = "sila123")
    private String password;
}
