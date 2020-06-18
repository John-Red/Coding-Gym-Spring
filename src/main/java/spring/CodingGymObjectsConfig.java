package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CodingGymObjectsConfig {

    @Bean
    @Scope(value = "CodingGym")
    public static SomeObject firstObject() {
        return new SomeObject();
    }

    @Bean
    @Scope(value = "CodingGym")
    public static SomeObject secondObject() {
        return new SomeObject();
    }
}
