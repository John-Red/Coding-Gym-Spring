/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ApplicationTest {
    @Test
    public void testAppHasAGreeting() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CodingGymObjectsConfig.class, CodingGymBeanFactoryPostProcessor.class)) {

            Map<String, SomeObject> codingGymBeans = context.getBeansOfType(SomeObject.class);

            assertThat(codingGymBeans.size(), equalTo(2));

            String fooDefinition = context.getBeanDefinition("firstObject").getScope();
            String barDefinition = context.getBeanDefinition("secondObject").getScope();

            assertThat(fooDefinition, equalTo("CodingGym"));
            assertThat(barDefinition, equalTo("CodingGym"));
        }
    }
}
