package spring;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Prototype {

    @InjectRandomInt(min = 100, max = 500)
    private int randomInt;

    public Prototype(){
        System.out.println("Prototype is ALIVE");
    }

    public Integer getRandomInt (){
        return randomInt;
    }
}
