package com.corporate.project.laza_api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reaction")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ReactionRestController {
}
