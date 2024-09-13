package com.corporate.project.laza_api.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthPasswordChangeRequest {
    @NotEmpty(message = "newPassword can't be blank")
    @Length(min = 8,message = "newPassword must be at least 8'")
    @Schema(name = "newPassword",example = "admin123",defaultValue = "sila123")
    private String newPassword;
    @NotEmpty(message = "oldPassword can't be blank")
    @Length(min = 8,message = "oldPassword must be at least 8'")
    @Schema(name = "oldPassword",example = "admin123",defaultValue = "sila123")
    private String oldPassword;
}
