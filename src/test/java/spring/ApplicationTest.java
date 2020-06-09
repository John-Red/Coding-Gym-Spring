/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package spring;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotEquals;

public class ApplicationTest {
    @Test
    public void testAppHasAGreeting() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(Singleton.class);
            context.register(Prototype.class);
            context.refresh();

            Singleton firstSingleton = context.getBean(Singleton.class);
            Prototype firstPrototype = firstSingleton.getPrototype();

            Singleton secondSingleton = context.getBean(Singleton.class);
            Prototype secondPrototype = secondSingleton.getPrototype();

            assertNotEquals(firstPrototype, secondPrototype);
        }
    }
}
