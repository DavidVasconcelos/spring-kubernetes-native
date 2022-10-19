package com.spring.springreactive.repository

import com.spring.springreactive.entity.CustomerEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : ReactiveCrudRepository<CustomerEntity, Int>