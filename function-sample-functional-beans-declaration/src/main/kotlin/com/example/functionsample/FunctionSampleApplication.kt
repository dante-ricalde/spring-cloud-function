package com.example.functionsample

import org.springframework.boot.SpringBootConfiguration
import org.springframework.cloud.function.context.FunctionRegistration
import org.springframework.cloud.function.context.FunctionType
import org.springframework.cloud.function.context.FunctionalSpringApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import java.util.function.Function
import java.util.function.Supplier

@SpringBootConfiguration
class FunctionSampleApplication : ApplicationContextInitializer<GenericApplicationContext> {

    fun uppercaseFunctionalStyle(): Function<String, String> {
        return Function { it.toUpperCase() }
    }

    override fun initialize(context: GenericApplicationContext) {
        context.registerBean(
            "demo", FunctionRegistration::class.java,
            Supplier {
                FunctionRegistration(uppercaseFunctionalStyle())
                    .type(
                        FunctionType
                            .from(String::class.java)
                            .to(String::class.java)
                    )
            }
        )
    }

    /*companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            FunctionalSpringApplication.run(FunctionSampleApplication::class.java, *args)
        }
    }*/
}

fun main(args: Array<String>) {
    FunctionalSpringApplication.run(FunctionSampleApplication::class.java, *args)
}
