package spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@MeasureTime
public class SomeObject {

    void doSomething() {
        while (true) {
            System.out.println("working");
        }
    }
}
