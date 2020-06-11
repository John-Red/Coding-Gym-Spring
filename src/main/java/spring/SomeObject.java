package spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SomeObject {

    @InjectDefaultValue(value = "someObject")
    private String someValue;

    public String getSomeValue() {
        return someValue;
    }
}
