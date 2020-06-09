package spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Singleton {

    public Singleton() {
        log.info("Singleton was created");
    }

    @Lookup
    public Prototype getPrototype() {
        return null;
    }
}
