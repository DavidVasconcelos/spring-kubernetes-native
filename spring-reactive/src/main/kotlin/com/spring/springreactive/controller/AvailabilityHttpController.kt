package com.spring.springreactive.controller

import org.springframework.boot.availability.AvailabilityChangeEvent
import org.springframework.boot.availability.LivenessState
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import kotlin.jvm.Throws

@Controller
@ResponseBody
class AvailabilityHttpController(val context: ApplicationContext) {

    @GetMapping("/down")
    fun down() = AvailabilityChangeEvent.publish(context, LivenessState.BROKEN)

    @GetMapping("/slow")
    @Throws(Exception::class)
    fun slow() {
        Thread.sleep(10_000)
    }

}