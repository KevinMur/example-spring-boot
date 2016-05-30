package io.dweomer.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExampleApplicationTest {

    @Test
    public void testHome() {
        assertEquals("Hello Docker World", new ExampleApplication().home());
    }

}
