package com.corporate.project.laza_api.model.apiresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ApiResponse<T> {

    private String message;
    private T payload;
    private Integer status;
}

