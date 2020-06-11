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

        SomeObject someObject = context.getBean(SomeObject.class);
        String someValue = someObject.getSomeValue();

        System.out.println(someValue);
    }
}
