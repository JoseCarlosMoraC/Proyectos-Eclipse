 package acceso.myshop.controllers;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "acceso.myshop")  // <--- indica a Spring que escanee todo acceso.myshop
public class ClasePrincipal {

    public static void main(String[] args) {
        SpringApplication.run(ClasePrincipal.class, args);
    }
}
  