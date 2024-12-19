package org.example.final_project.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Person {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty contact;

    public Person(int id, String name, String contact) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.contact = new SimpleStringProperty(contact);
    }

    public int getId() {
        return this.id.get();
    }

    public String getName() {
        return this.name.get();
    }

    public String getContact() {
        return this.contact.get();
    }
}