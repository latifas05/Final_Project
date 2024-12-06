package org.example.final_project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pet {
    private IntegerProperty adoptionId;
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty breed;
    private final StringProperty type;
    private final IntegerProperty age;
    private final StringProperty description;

    public Pet(int id, String name, String breed, String type, int age, String description) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.breed = new SimpleStringProperty(breed);
        this.type = new SimpleStringProperty(type);
        this.age = new SimpleIntegerProperty(age);
        this.description = new SimpleStringProperty(description);
        this.adoptionId = new SimpleIntegerProperty();
    }

    public int getId() {
        return this.id.get();
    }

    public IntegerProperty idProperty() {
        return this.id;
    }

    public String getName() {
        return (String)this.name.get();
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public String getBreed() {
        return (String)this.breed.get();
    }

    public StringProperty breedProperty() {
        return this.breed;
    }

    public String getType() {
        return (String)this.type.get();
    }

    public StringProperty typeProperty() {
        return this.type;
    }

    public int getAge() {
        return this.age.get();
    }

    public IntegerProperty ageProperty() {
        return this.age;
    }

    public String getDescription() {
        return (String)this.description.get();
    }

    public StringProperty descriptionProperty() {
        return this.description;
    }

    public int getAdoptionId() {
        return this.adoptionId.get();
    }

    public IntegerProperty adoptionIdProperty() {
        return this.adoptionId;
    }
}

