package com.spring.service.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("customer")
data class CustomerEntity(@Id val id: Int, val name: String)
