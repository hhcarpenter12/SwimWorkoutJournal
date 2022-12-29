package com.swimworkout.swimworkout

import com.samskivert.mustache.DefaultCollector
import com.samskivert.mustache.Mustache
import com.samskivert.mustache.Mustache.TemplateLoader
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class SwimWorkoutApplication

fun main(args: Array<String>) {
    runApplication<SwimWorkoutApplication>(*args)

    val ngrokClient: NgrokClient = Builder().build()
    val createTunnel: CreateTunnel = Builder()
        .withAddr(8080)
        .build()
    val tunnel: Tunnel = ngrokClient.connect(createTunnel)
}

@Bean
fun mustacheCompiler(templateLoader: TemplateLoader?): Mustache.Compiler? {
    return Mustache.compiler()
        .defaultValue("Some Default Value")
        .withLoader(templateLoader)
        .withCollector(DefaultCollector())
}
