package spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Singleton {
    @Autowired
    private Prototype prototype;

    public Singleton(){
        log.info("was created");
    }

    public Prototype getPrototype (){
        return prototype;
    }

}
