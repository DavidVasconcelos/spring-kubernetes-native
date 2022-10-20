package com.spring.configuration

import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform
import org.springframework.boot.cloud.CloudPlatform
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.core.env.Environment
import org.springframework.core.env.getProperty

@Configuration
class ListenerConfiguration(val environment: Environment) {

    @EventListener(ApplicationReadyEvent::class, RefreshScopeRefreshedEvent::class)
    fun begin() {
        println("The message is ${environment.getProperty<Any>("message")}")
    }

    @Bean
    @ConditionalOnCloudPlatform(CloudPlatform.KUBERNETES)
    fun readyEventApplicationListener() =
        ApplicationListener<ApplicationReadyEvent> { println("The application is running on K8s") }
}