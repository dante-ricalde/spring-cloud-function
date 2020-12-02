package com.example.functionsample

import mu.KotlinLogging.logger
import org.springframework.boot.SpringBootConfiguration
import org.springframework.cloud.function.context.FunctionalSpringApplication
import java.util.function.Function

/**
 * [Description].
 * <br/>
 * <b>Class</b>: {@link DemoApplicationItselfImplementsFunction}<br/>
 * <b>Copyright</b>: &Copy; 2020 Banco de Cr&eacute;dito del Per&uacute;. <br/>
 * <b>Company</b>: Banco de Cr&eacute;dito del Per&uacute;. <br/>
 *
 * @author Banco de Cr&eacute;dito del Per&uacute;. (BCP) <br/>
 *         <u>Service Provider</u>: Everis Per&uacute; SAC (EVE) <br/>
 *         <u>Developed by</u>: <br/>
 *         <ul>
 *         <li>Dante Raphael Ricalde Delgado. (DRD) From (BCP)</li>
 *         </ul>
 *         <u>Changes</u>:<br/>
 *         <ul>
 *         <li>Nov 22, 2020 (DRD) Creation class.</li>
 *         </ul>
 * @version 1.0
 */
private val logger = logger {}

@SpringBootConfiguration
class DemoApplicationItselfImplementsFunction : Function<String, String> {

    override fun apply(value: String): String {
        logger.info { "uppercasing value => $value" }
        return value.toUpperCase()
    }
}

fun main(args: Array<String>) {
    FunctionalSpringApplication.run(DemoApplicationItselfImplementsFunction::class.java, *args)
}
