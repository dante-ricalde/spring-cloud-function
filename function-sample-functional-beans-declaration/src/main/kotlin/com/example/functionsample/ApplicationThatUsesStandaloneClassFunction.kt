package com.example.functionsample

import com.example.functionsample.function.StreamUppercaseFunction
import org.springframework.boot.SpringBootConfiguration
import org.springframework.cloud.function.context.FunctionRegistration
import org.springframework.cloud.function.context.FunctionalSpringApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import java.util.function.Supplier

/**
 * [Description].
 * <br/>
 * <b>Class</b>: {@link StreamUppercaseFunctionDemoApplication}<br/>
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
 *         <li>Nov 23, 2020 (DRD) Creation class.</li>
 *         </ul>
 * @version 1.0
 */
@SpringBootConfiguration
class ApplicationThatUsesStandaloneClassFunction : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(context: GenericApplicationContext) {
        context.registerBean(
            "function", FunctionRegistration::class.java,
            Supplier { FunctionRegistration(StreamUppercaseFunction()).type(StreamUppercaseFunction::class.java) }
        )
    }
}

fun main(args: Array<String>) {
    FunctionalSpringApplication.run(ApplicationThatUsesStandaloneClassFunction::class.java, *args)
}
