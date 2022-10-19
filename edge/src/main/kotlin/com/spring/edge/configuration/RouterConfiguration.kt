package com.spring.edge.configuration

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders


@Configuration
class RouterConfiguration {

    @Bean
    fun gateway(rlb: RouteLocatorBuilder): RouteLocator {
        return rlb
            .routes()
            .route { rs: PredicateSpec ->
                rs.path("/proxy")
                    .filters { fs: GatewayFilterSpec ->
                        fs.setPath("/customers")
                            .addResponseHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                            .retry(10)
                    }.uri("http://localhost:8080")
            }.build()
    }
}