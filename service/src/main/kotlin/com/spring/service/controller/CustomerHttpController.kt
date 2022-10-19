package com.spring.service.controller

import com.spring.service.entity.CustomerEntity
import com.spring.service.repository.CustomerRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Flux

@Controller
@ResponseBody
class CustomerHttpController(val customerRepository: CustomerRepository) {

    @GetMapping("/customers")
    fun get(): Flux<CustomerEntity> = customerRepository.findAll()
}