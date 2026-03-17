package com.booking.platform.config;

import com.booking.platform.security.MD5Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeenConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new MD5Encoder();
    }

//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI().addSecurityItem(new SecurityRequirement()
//                .addList("Bearer Authentication"))
//                .components(new Components().addSecuritySchemes("Bearer Authentication",createApiSecuritySchema()));
//    }
//
//    private SecurityScheme createApiSecuritySchema() {
//        return  new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                .bearerFormat("JWT")
//                .scheme("bearer");
//    }
}
