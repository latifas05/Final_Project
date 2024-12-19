package org.example.final_project.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pet implements Adoptable {
    private final IntegerProperty id; // Use IntegerProperty for JavaFX binding
    private final StringProperty name;
    private final StringProperty breed;
    private final StringProperty type;
    private final IntegerProperty age;
    private final StringProperty description;

    public Pet(int id, String name, String breed, String type, int age, String description) {
        this.id = new SimpleIntegerProperty(id); // Initialize the id property
        this.name = new SimpleStringProperty(name);
        this.breed = new SimpleStringProperty(breed);
        this.type = new SimpleStringProperty(type);
        this.age = new SimpleIntegerProperty(age); // Initialize the age property
        this.description = new SimpleStringProperty(description);
    }

    @Override
    public void adopt() {
        System.out.println(getName() + " has been adopted!");
    }

    // Getters
    public int getId() {
        return this.id.get(); // Correctly get the value of the IntegerProperty
    }

    public String getName() {
        return this.name.get();
    }

    public String getBreed() {
        return this.breed.get();
    }

    public String getType() {
        return this.type.get();
    }

    public int getAge() {
        return this.age.get(); // Correctly get the value of the IntegerProperty
    }

    public String getDescription() {
        return this.description.get();
    }

    // Property methods for JavaFX bindings
    public IntegerProperty idProperty() {
        return this.id;
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public StringProperty breedProperty() {
        return this.breed;
    }

    public StringProperty typeProperty() {
        return this.type;
    }

    public IntegerProperty ageProperty() {
        return this.age;
    }

    public StringProperty descriptionProperty() {
        return this.description;
    }
}