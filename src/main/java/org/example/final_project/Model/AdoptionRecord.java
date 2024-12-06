package org.example.final_project;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdoptionRecord {
    private final IntegerProperty adoptionId;
    private final IntegerProperty adopterId;
    private final StringProperty adopterName;
    private final StringProperty adopterContact;
    private final IntegerProperty petId;
    private final StringProperty petName;
    private final StringProperty petType;
    private final ObjectProperty<LocalDate> adoptionDate;

    public AdoptionRecord(int adoptionId, int adopterId, int petId, String adopterName, String adopterContact, String petName, String petType, LocalDate adoptionDate) {
        this.adoptionId = new SimpleIntegerProperty(adoptionId);
        this.adopterId = new SimpleIntegerProperty(adopterId);
        this.petId = new SimpleIntegerProperty(petId);
        this.adopterName = new SimpleStringProperty(adopterName);
        this.adopterContact = new SimpleStringProperty(adopterContact);
        this.petName = new SimpleStringProperty(petName);
        this.petType = new SimpleStringProperty(petType);
        this.adoptionDate = new SimpleObjectProperty(adoptionDate);
    }

    public int getAdoptionId() {
        return this.adoptionId.get();
    }

    public void setAdoptionId(int adoptionId) {
        this.adoptionId.set(adoptionId);
    }

    public IntegerProperty adoptionIdProperty() {
        return this.adoptionId;
    }

    public int getAdopterId() {
        return this.adopterId.get();
    }

    public void setAdopterId(int adopterId) {
        this.adopterId.set(adopterId);
    }

    public IntegerProperty adopterIdProperty() {
        return this.adopterId;
    }

    public String getAdopterName() {
        return (String)this.adopterName.get();
    }

    public void setAdopterName(String adopterName) {
        this.adopterName.set(adopterName);
    }

    public StringProperty adopterNameProperty() {
        return this.adopterName;
    }

    public String getAdopterContact() {
        return (String)this.adopterContact.get();
    }

    public void setAdopterContact(String adopterContact) {
        this.adopterContact.set(adopterContact);
    }

    public StringProperty adopterContactProperty() {
        return this.adopterContact;
    }

    public int getPetId() {
        return this.petId.get();
    }

    public void setPetId(int petId) {
        this.petId.set(petId);
    }

    public IntegerProperty petIdProperty() {
        return this.petId;
    }

    public String getPetName() {
        return (String)this.petName.get();
    }

    public void setPetName(String petName) {
        this.petName.set(petName);
    }

    public StringProperty petNameProperty() {
        return this.petName;
    }

    public String getPetType() {
        return (String)this.petType.get();
    }

    public void setPetType(String petType) {
        this.petType.set(petType);
    }

    public StringProperty petTypeProperty() {
        return this.petType;
    }

    public LocalDate getAdoptionDate() {
        return (LocalDate)this.adoptionDate.get();
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate.set(adoptionDate);
    }

    public ObjectProperty<LocalDate> adoptionDateProperty() {
        return this.adoptionDate;
    }
}

