package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Singleton {
    @Autowired
    private Prototype prototype;


    public Singleton(){
        System.out.println("Singleton: I'm alive");
    }

    //@Lookup
    public Prototype getPrototype (){
        return prototype;
    }

}
