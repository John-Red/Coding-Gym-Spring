package spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
@ProfilingAnnotation
public class Singleton implements BaseSingleton{

    public Singleton(){
        System.out.println("Singleton: I'm alive");
    }

    public void doSomething() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            System.out.println("I FEEL A GREAT POWER");
        }
    }
}
