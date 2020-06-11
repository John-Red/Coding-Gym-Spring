package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MeasureTimeBeanPostProcessor implements BeanPostProcessor {
    Map<String,Object> profiledBeans = new ConcurrentHashMap<>();


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        MeasureTime annotation = bean.getClass().getAnnotation(MeasureTime.class);
        if (Objects.nonNull(annotation)){
            profiledBeans.put(beanName,bean);
        }
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        return bean;
    }
}
