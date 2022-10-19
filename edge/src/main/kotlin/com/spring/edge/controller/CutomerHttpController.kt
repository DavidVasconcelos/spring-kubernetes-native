package com.spring.edge.controller

import com.spring.edge.model.Customer
import com.spring.edge.model.Profile
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import org.springframework.web.reactive.function.client.WebClient

@Controller
class CutomerHttpController(val http: WebClient) {

    @SchemaMapping(typeName = "Customer")
    fun profile(customer: Customer) = Profile(customer.id)

    @QueryMapping
    fun customers() = http.get().uri("http://localhost:8080/customers").retrieve().bodyToFlux(Customer::class.java)
}