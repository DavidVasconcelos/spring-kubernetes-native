package com.spring.service.repository

import com.spring.service.entity.CustomerEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : ReactiveCrudRepository<CustomerEntity, Int>