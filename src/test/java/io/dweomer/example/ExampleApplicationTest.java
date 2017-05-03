package io.dweomer.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExampleApplicationTest {

    @Test
    public void testHome() {
        assertEquals("Hello Docker World", new ExampleApplication().home());
    }

    @Test
    public void testEnvironmentVariables() {
        assertNotNull(new ExampleApplication().environmentVariables());
    }

    @Test
    public void testSystemProperties() {
        assertNotNull(new ExampleApplication().systemProperties());
    }

}
