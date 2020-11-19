package com.example.functionsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Flux
import java.util.function.Function

// @Import(KotlinLambdaToFunctionAutoConfiguration::class)
@SpringBootApplication
class FunctionSampleApplication {

    /*@Bean
    fun uppercase(): (Flux<String>) -> Flux<String> {
        return { flux -> flux.log().map { it.toUpperCase() } }
    }*/

    @Bean
    fun uppercase(): Function<Flux<String>, Flux<String>> {
        return Function { flux -> flux.log().map { it.toUpperCase() } }
    }
}

fun main(args: Array<String>) {
    runApplication<FunctionSampleApplication>(*args)
}
