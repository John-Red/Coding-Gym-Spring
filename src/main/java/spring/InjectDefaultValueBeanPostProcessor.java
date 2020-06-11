package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class InjectDefaultValueBeanPostProcessor implements BeanPostProcessor {

    YamlPropertySourceLoader yamlLoader = new YamlPropertySourceLoader();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectDefaultValue annotation = field.getAnnotation(InjectDefaultValue.class);
            if (Objects.nonNull(annotation)) {
                String annotationValue = annotation.value();
                String value = getValueFromProperties(annotationValue);
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, value);
            }
        }
        return bean;
    }

    private String getValueFromProperties(String valueName) {
        String value = "";
        try {
            List<PropertySource<?>> propertySource = yamlLoader.load("application", new ClassPathResource("application.yaml"));
            Optional<PropertySource<?>> first = propertySource.stream()
                    .filter(source -> source.containsProperty(valueName))
                    .findFirst();
            value = (String)first.orElseThrow(() -> new ClassNotFoundException()).getProperty(valueName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
