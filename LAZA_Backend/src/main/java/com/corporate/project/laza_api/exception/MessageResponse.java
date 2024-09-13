package com.corporate.project.laza_api.exception;

import lombok.Data;

@Data
public class MessageResponse {
    private int status;
    private String message;
}