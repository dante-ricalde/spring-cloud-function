package com.example.functionsample.function

import com.example.functionsample.model.Bar
import com.example.functionsample.model.Foo
import reactor.core.publisher.Flux
import java.util.function.Function

/**
 * [Description].
 * <br/>
 * <b>Class</b>: {@link StreamUppercase}<br/>
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
class StreamUppercaseFunction : Function<Flux<Foo>, Flux<Bar>> {

    override fun apply(flux: Flux<Foo>): Flux<Bar> {
        return flux.log().map { foo -> Bar("This is a Bar object from Foo value => ${foo.value}") }
    }
}
