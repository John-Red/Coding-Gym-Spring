package spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@Profiling
public class Singleton implements BaseSingleton{
    @Autowired
    private Prototype prototype;


    public Singleton(){
        log.info("Singleton: I'm alive");
    }

    @Lookup
    public Prototype getPrototype (){
        return null;
    }

    public void doSomething() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            System.out.println("I FEEL A GREAT POWER");
        }
    }
}
