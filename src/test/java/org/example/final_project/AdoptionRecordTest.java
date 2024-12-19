package org.example.final_project;

import java.time.LocalDate;

import org.example.final_project.Model.AdoptionRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdoptionRecordTest {

    @Test
    public void testAdoptionRecordConstructor() {
        LocalDate adoptionDate = LocalDate.of(2024, 12, 6);
        AdoptionRecord record = new AdoptionRecord(1, 1, 1, "John Doe", "555-1234", "Buddy", "Dog", adoptionDate);

        // Test getter methods
        assertEquals(1, record.getAdoptionId());
        assertEquals(1, record.getAdopterId());
        assertEquals("John Doe", record.getAdopterName());
        assertEquals("555-1234", record.getAdopterContact());
        assertEquals(1, record.getPetId());
        assertEquals("Buddy", record.getPetName());
        assertEquals("Dog", record.getPetType());
        assertEquals(adoptionDate, record.getAdoptionDate());
    }

    @Test
    public void testSetAdopterName() {
        LocalDate adoptionDate = LocalDate.of(2024, 12, 6);
        AdoptionRecord record = new AdoptionRecord(1, 1, 1, "John Doe", "555-1234", "Buddy", "Dog", adoptionDate);
        record.setAdopterName("Jane Doe");

        // Test if setter works
        assertEquals("Jane Doe", record.getAdopterName());
    }

    @Test
    public void testSetAdoptionDate() {
        LocalDate adoptionDate = LocalDate.of(2024, 12, 6);
        LocalDate newAdoptionDate = LocalDate.of(2024, 12, 7);
        AdoptionRecord record = new AdoptionRecord(1, 1, 1, "John Doe", "555-1234", "Buddy", "Dog", adoptionDate);
        record.setAdoptionDate(newAdoptionDate);

        // Test if setter works
        assertEquals(newAdoptionDate, record.getAdoptionDate());
    }
}
