package com.example.functionsample;

import java.util.function.Function;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

/**
 * [Description]. <br/>
 * <b>Class</b>: {@link FunctionJavaSampleApplication}<br/>
 * <b>Copyright</b>: &Copy; 2020 Banco de Cr&eacute;dito del Per&uacute;. <br/>
 * <b>Company</b>: Banco de Cr&eacute;dito del Per&uacute;. <br/>
 *
 * @author Banco de Cr&eacute;dito del Per&uacute;. (BCP) <br/>
 * <u>Service Provider</u>: Everis Per&uacute; SAC (EVE) <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>Dante Raphael Ricalde Delgado. (DRD) From (BCP)</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>Nov 20, 2020 (DRD) Creation class.</li>
 * </ul>
 * @version 1.0
 */
//@SpringBootConfiguration
public class FunctionJavaSampleApplication implements
    ApplicationContextInitializer<GenericApplicationContext> {

  /*public static void main(String[] args) {
    FunctionalSpringApplication.run(FunctionJavaSampleApplication.class, args);
  }*/

  public Function<String, String> uppercaseFunctionalStyle() {
    return String::toUpperCase;
  }

  @Override
  public void initialize(GenericApplicationContext context) {
    context.registerBean("demo", FunctionRegistration.class,
        () -> new FunctionRegistration<>(uppercaseFunctionalStyle())
            .type(FunctionType.from(String.class).to(String.class)));
  }
}
