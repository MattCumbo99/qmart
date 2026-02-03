package com.mattrition.qmart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class QmartApplication

fun main(args: Array<String>) {
    runApplication<QmartApplication>(*args)
}
