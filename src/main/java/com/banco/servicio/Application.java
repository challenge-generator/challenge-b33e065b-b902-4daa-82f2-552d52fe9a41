package com.banco.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Configuración básica de rutas para health check y métricas.
     * Esto permite verificar el estado del servicio durante el despliegue en contenedores.
     */
    @Bean
    RouterFunction<ServerResponse> actuatorRoutes() {
        return route(RequestPredicates.GET("/actuator/health"),
            request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just("{\"status\":\"UP\"}"), String.class))
            .andRoute(RequestPredicates.GET("/actuator/info"),
                request -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just("{\"app\":\"servicio-transacciones\"}"), String.class));
    }
}