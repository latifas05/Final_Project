package org.example.final_project;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adopter {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty contact;

    public Adopter(int id, String name, String contact) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.contact = new SimpleStringProperty(contact);
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

    public String getContact() {
        return (String)this.contact.get();
    }

    public StringProperty contactProperty() {
        return this.contact;
    }
}

