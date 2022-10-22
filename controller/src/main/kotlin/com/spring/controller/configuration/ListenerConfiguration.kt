package com.spring.controller.configuration

import com.spring.controller.extension.toSystemTimeZone
import io.kubernetes.client.openapi.ApiClient
import io.kubernetes.client.openapi.models.V1Pod
import io.kubernetes.client.openapi.models.V1PodList
import io.kubernetes.client.util.generic.GenericKubernetesApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.Assert
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


@Configuration
class ListenerConfiguration {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun v1PodV1PodListGenericKubernetesApi(apiClient: ApiClient): GenericKubernetesApi<V1Pod, V1PodList> {
        return GenericKubernetesApi(
            V1Pod::class.java, V1PodList::class.java, "", "v1", "pods", apiClient
        )
    }

    @Bean
    fun runner(api: GenericKubernetesApi<V1Pod, V1PodList>): ApplicationListener<ApplicationReadyEvent> {
        return ApplicationListener<ApplicationReadyEvent> {
            val response = api.list("ingress-nginx") //$ minikube addons enable ingress ;)
            Assert.state(response.isSuccess) { "the call to query a Pod was not successful" }
            val pods = response.`object`.items.map { mapPod(it) }
                .toMutableList()
                .joinToString(prefix = "\nname;status;age \n", separator = "")
            logger.info(pods)
        }
    }

    private fun mapPod(pod: V1Pod): String {
        val age = pod.status?.startTime?.toLocalDateTime()?.let { calculateAge(it) } ?: "NONE"
        return "${pod.metadata?.name};${pod.status?.phase};$age \n"
    }

    private fun calculateAge(startTime: LocalDateTime): String {
        val minutes = ChronoUnit.MINUTES.between(startTime.toSystemTimeZone(), LocalDateTime.now())
        return when {
            minutes > DAY_IN_MINUTES -> "${minutes.div(DAY_IN_MINUTES)}D"
            minutes > HOUR_IN_MINUTES -> "${minutes.div(HOUR_IN_MINUTES)}H"
            else -> "${minutes}M"
        }
    }

    companion object {
        const val DAY_IN_MINUTES = 1440
        const val HOUR_IN_MINUTES = 60
    }
}