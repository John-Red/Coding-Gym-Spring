package spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ProfilingBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Class> map = new HashMap<>();
    ProfilingController profilingController = new ProfilingController();

    public ProfilingBeanPostProcessor() throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        platformMBeanServer.registerMBean(profilingController,new ObjectName("profiling","name", "controller"));
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(ProfilingAnnotation.class)) {
            map.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = map.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                  //  if (true) {
                        log.info("Profiling");
                        Instant before = Instant.now();
                        Object invoke = method.invoke(bean, args);
                        Instant after = Instant.now();
                        log.info("Done");
                        long result = after.toEpochMilli() - before.toEpochMilli();
                        log.info("miliseconds to run = " + result);
                        return invoke;
//                    } else {
//                        return method.invoke(bean, args);
//                    }
                }
            });
        }
        return bean;
    }
}
