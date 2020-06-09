package spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Singleton {

    @InjectDefaultValue(value = "singleton")
    String someValue;

    public Singleton() {
        log.info("Singleton was created");
    }


    @PostConstruct
    void init(){
        System.out.println(someValue);
    }

    @Lookup
    public Prototype getPrototype() {
        return null;
    }
}
