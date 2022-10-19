package com.spring.springreactive.config

import com.spring.springreactive.entity.CustomerEntity
import com.spring.springreactive.repository.CustomerRepository
import org.springframework.boot.availability.AvailabilityChangeEvent
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux

@Configuration
class ListenerConfig {

    @Bean
    fun availabilityChangeEventApplicationListener(): ApplicationListener<AvailabilityChangeEvent<*>> {
        return ApplicationListener { event: AvailabilityChangeEvent<*> ->
            println(event.resolvableType.toString() + ":" + event.state)
        }
    }

    @Bean
    fun applicationReadyEventApplicationListener(customerRepository: CustomerRepository):
            ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener<ApplicationReadyEvent> {
            Flux.just("Guga", "Fabinho", "Borracha", "Syroff")
                .map { name -> CustomerEntity(0, name) }
                .flatMap(customerRepository::save)
                .subscribe(System.out::println)
        }
    }
}