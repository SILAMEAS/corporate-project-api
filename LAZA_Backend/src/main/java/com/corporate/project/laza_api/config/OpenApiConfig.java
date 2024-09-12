package com.corporate.project.laza_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "LAZA API ",
        version = "v1",
        description = "This is description"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer", // Use uppercase "Bearer"
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
