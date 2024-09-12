package com.corporate.project.laza_api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthPasswordChangeRequest {
    private String newPassword;
    private String oldPassword;
}
