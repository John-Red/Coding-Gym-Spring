package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MeasureTimeBeanPostProcessor implements BeanPostProcessor {
    Map<String, Class> profiledBeans = new ConcurrentHashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        final Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(MeasureTime.class)) {
            profiledBeans.put(beanName, beanClass);
        }
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = profiledBeans.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    final long startTime = Instant.now().toEpochMilli();

                    final Object invoke = method.invoke(bean, args);

                    final long endTime = Instant.now().toEpochMilli();
                    final long progressTime = endTime - startTime;
                    System.out.println("Time to execute = " + progressTime);
                    return invoke;
                }
            });
        }
        return bean;
    }
}
