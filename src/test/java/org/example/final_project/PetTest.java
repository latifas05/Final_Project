package org.example.final_project;

import org.example.final_project.Model.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    @Test
    public void testPetConstructor() {
        Pet pet = new Pet(1, "Buddy", "Golden Retriever", "Dog", 5, "Friendly and playful");

        // Test getter methods
        assertEquals(1, pet.getId());
        assertEquals("Buddy", pet.getName());
        assertEquals("Golden Retriever", pet.getBreed());
        assertEquals("Dog", pet.getType());
        assertEquals(5, pet.getAge());
        assertEquals("Friendly and playful", pet.getDescription());
    }

    @Test
    public void testSetAdoptionId() {
        Pet pet = new Pet(1, "Buddy", "Golden Retriever", "Dog", 5, "Friendly and playful");
        pet.adoptionIdProperty().set(101);

        // Test if adoptionId is set correctly
        assertEquals(101, pet.getAdoptionId());
    }
}
