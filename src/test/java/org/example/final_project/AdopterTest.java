package org.example.final_project;

import org.example.final_project.Model.Adopter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdopterTest {

    @Test
    public void testAdopterConstructor() {
        Adopter adopter = new Adopter(1, "John Doe", "555-1234");

        // Test getter methods
        assertEquals(1, adopter.getId());
        assertEquals("John Doe", adopter.getName());
        assertEquals("555-1234", adopter.getContact());
    }
}
