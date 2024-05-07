package com.example.greengram.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
                title = "그린그램",
                description = "Greengram with React",
                version = "v1"
        )

)
public class SwaggerConfiguration {
}
