package spring;

import org.springframework.stereotype.Component;

@Component
@MeasureTime
public class SomeObject implements BaseObject {

    @Override
    public void doSomething() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("working");
        }
    }
}
