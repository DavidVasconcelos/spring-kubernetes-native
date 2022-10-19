package com.spring.springreactive.controller

import com.spring.springreactive.entity.CustomerEntity
import com.spring.springreactive.repository.CustomerRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Flux

@Controller
@ResponseBody
class CustomerController(val customerRepository: CustomerRepository) {

    @GetMapping("/customers")
    fun get(): Flux<CustomerEntity> = customerRepository.findAll()
}