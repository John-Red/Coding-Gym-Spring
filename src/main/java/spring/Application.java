package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class);

        while (true) {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            context.getBean(BaseSingleton.class).doSomething();
        }
    }
}
