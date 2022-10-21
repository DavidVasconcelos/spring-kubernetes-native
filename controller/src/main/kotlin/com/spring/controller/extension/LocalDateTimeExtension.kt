package com.spring.controller.extension

import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toSystemTimeZone(): LocalDateTime =
    this.atZone(ZoneId.of("UTC"))
        .withZoneSameInstant(ZoneId.systemDefault())
        .toLocalDateTime()
