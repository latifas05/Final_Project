package org.example.final_project.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adopter extends Person {
    private final IntegerProperty id; // Use IntegerProperty for JavaFX binding

    public Adopter(int id, String name, String contact) {
        super(id, name, contact); // Call the Person constructor with id, name, and contact
        this.id = new SimpleIntegerProperty(id); // Initialize the id property
    }

    public int getId() {
        return this.id.get();
    }

    public IntegerProperty idProperty() {
        return this.id;
    }

    public String getName() {
        return super.getName(); // Call the getName method from Person
    }

    public String getContact() {
        return super.getContact(); // Call the getContact method from Person
    }

    // Add property methods for JavaFX bindings if needed
    public StringProperty nameProperty() {
        return new SimpleStringProperty(getName());
    }

    public StringProperty contactProperty() {
        return new SimpleStringProperty(getContact());
    }
}