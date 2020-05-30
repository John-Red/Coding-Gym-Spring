package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class);

        Singleton firstSingleton = context.getBean(Singleton.class);
        Prototype firstPrototype = firstSingleton.getPrototype();

        Singleton secondSingleton = context.getBean(Singleton.class);
        Prototype secProt = secondSingleton.getPrototype();

        System.out.println(firstPrototype.equals(secProt));
    }
}
