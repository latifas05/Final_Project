package org.example.final_project;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.final_project.DAO.AdoptersDAO;
import org.example.final_project.DAO.AdoptionRecordsDAO;
import org.example.final_project.DAO.PetDAO;
import org.example.final_project.Model.*;
import java.util.List;

public class HelloController {
    private PetDAO petDAO;
    private AdoptionRecordsDAO adoptionRecordsDAO;
    private AdoptersDAO adoptersDAO;

    // No-argument constructor
    public HelloController() {
        // You can initialize your DAOs here if needed
        this.petDAO = new PetDAO(); // Example initialization
        this.adoptionRecordsDAO = new AdoptionRecordsDAO(); // Example initialization
        this.adoptersDAO = new AdoptersDAO(); // Example initialization
    }

    // Your existing constructor with parameters
    public HelloController(PetDAO petDAO, AdoptionRecordsDAO adoptionRecordsDAO, AdoptersDAO adoptersDAO) {
        this.petDAO = petDAO;
        this.adoptionRecordsDAO = adoptionRecordsDAO;
        this.adoptersDAO = adoptersDAO;
    }

    @FXML
    private TableView<Pet> petsTableView;
    @FXML
    private TableColumn<Pet, Integer> idColumn;
    @FXML
    private TableColumn<Pet, String> nameColumn;
    @FXML
    private TableColumn<Pet, String> breedColumn;
    @FXML
    private TableColumn<Pet, String> typeColumn;
    @FXML
    private TableColumn<Pet, Integer> ageColumn;
    @FXML
    private TableColumn<Pet, String> descriptionColumn;
    @FXML
    private ComboBox<String> typeFilterComboBox;
    @FXML
    private TextField adopterNameField;
    @FXML
    private TextField adopterContactField;
    @FXML
    private TextField addAdopterNameField;
    @FXML
    private TextField addAdopterContactField;
    @FXML
    private Button viewAdoptersButton;
    @FXML
    private ComboBox<Integer> adopterIdComboBox;
    @FXML
    private TableView<AdoptionRecord> adoptionRecordsTableView;
    @FXML
    private TableColumn<AdoptionRecord, Integer> adoptionIdColumn;
    @FXML
    private TableColumn<AdoptionRecord, String> adopterNameColumn;
    @FXML
    private TableColumn<AdoptionRecord, String> petNameColumn;
    @FXML
    private TableColumn<AdoptionRecord, String> petTypeColumn;
    @FXML
    private TableColumn<AdoptionRecord, LocalDate> adoptionDateColumn;
    @FXML
    private TableColumn<AdoptionRecord, String> adopterContactColumn;



    @FXML
    public void initialize() {
        this.loadPets();
        this.setupTypeFilterComboBox();
        this.setupTableView();
        this.setupAdoptionRecordsTableView();
        this.loadAdoptionRecords();
        this.loadAdopterIds();
    }

    private void setupTableView() {
        this.idColumn.setCellValueFactory((cellData) -> {
            return ((Pet)cellData.getValue()).idProperty().asObject();
        });
        this.nameColumn.setCellValueFactory((cellData) -> {
            return ((Pet)cellData.getValue()).nameProperty();
        });
        this.breedColumn.setCellValueFactory((cellData) -> {
            return ((Pet)cellData.getValue()).breedProperty();
        });
        this.typeColumn.setCellValueFactory((cellData) -> {
            return ((Pet)cellData.getValue()).typeProperty();
        });
        this.ageColumn.setCellValueFactory((cellData) -> {
            return ((Pet)cellData.getValue()).ageProperty().asObject();
        });
        this.descriptionColumn.setCellValueFactory((cellData) -> {
            return ((Pet)cellData.getValue()).descriptionProperty();
        });
    }

    private void setupAdoptionRecordsTableView() {
        this.adoptionIdColumn.setCellValueFactory((cellData) -> {
            return ((AdoptionRecord)cellData.getValue()).adoptionIdProperty().asObject();
        });
        this.adopterNameColumn.setCellValueFactory((cellData) -> {
            return ((AdoptionRecord)cellData.getValue()).adopterNameProperty();
        });
        this.petNameColumn.setCellValueFactory((cellData) -> {
            return ((AdoptionRecord)cellData.getValue()).petNameProperty();
        });
        this.petTypeColumn.setCellValueFactory((cellData) -> {
            return ((AdoptionRecord)cellData.getValue()).petTypeProperty();
        });
        this.adoptionDateColumn.setCellValueFactory((cellData) -> {
            return ((AdoptionRecord)cellData.getValue()).adoptionDateProperty();
        });
        this.adopterContactColumn.setCellValueFactory((cellData) -> {
            return ((AdoptionRecord)cellData.getValue()).adopterContactProperty();
        });
    }

    private void setupTypeFilterComboBox() {
        this.typeFilterComboBox.getItems().addAll(new String[]{"All", "Cat", "Dog", "Bird", "Other"});
        this.typeFilterComboBox.setValue("All");
        this.typeFilterComboBox.setOnAction((event) -> {
            this.loadPets();
        });
    }

    private void loadPets() {
        String selectedType = (String)this.typeFilterComboBox.getValue();
        this.petsTableView.getItems().clear();
        Iterator var2 = this.petDAO.getAllPets(selectedType).iterator();

        while(var2.hasNext()) {
            Pet pet = (Pet)var2.next();
            this.petsTableView.getItems().add(pet);
        }

    }

    private void loadAdopterIds() {
        List<Integer> adopterIds = adoptersDAO.getAllAdopterIds(); // Use the instance variable
        this.adopterIdComboBox.getItems().clear();
        this.adopterIdComboBox.getItems().addAll(adopterIds);
    }


    @FXML
    public void handleAdoption() {
        Pet selectedPet = (Pet) this.petsTableView.getSelectionModel().getSelectedItem();

        if (selectedPet != null) {
            String adopterName = this.adopterNameField.getText().trim();
            String adopterContact = this.adopterContactField.getText().trim();

            if (!adopterName.isEmpty() && !adopterContact.isEmpty()) {
                try {
                    int adopterId = this.getAdopterId();
                    int petId = selectedPet.getId();
                    LocalDate adoptionDate = LocalDate.now();

                    AdoptionRecord newAdoptionRecord = new AdoptionRecord(0, adopterId, petId, adopterName, adopterContact, selectedPet.getName(), selectedPet.getType(), adoptionDate);
                    this.adoptionRecordsDAO.addAdoptionRecord(newAdoptionRecord); // Use the correct variable name

                    this.petsTableView.getItems().remove(selectedPet);
                    this.petDAO.removePet(selectedPet);

                    this.loadAdoptionRecords();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Adoption Successful");
                    alert.setHeaderText(null);
                    alert.setContentText("Adoption has been successfully recorded, and the pet has been removed from the list. Thank you for adopting!");
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred while recording the adoption. Please try again.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Incomplete Information");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all adopter details.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Pet Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a pet to adopt.");
            alert.showAndWait();
        }
    }

    private void loadAdoptionRecords() {
        this.adoptionRecordsTableView.getItems().clear();
        List<AdoptionRecord> records = AdoptionRecordsDAO.getAllRecords();
        Iterator var2 = records.iterator();

        while(var2.hasNext()) {
            AdoptionRecord record = (AdoptionRecord)var2.next();
            this.adoptionRecordsTableView.getItems().add(record);
        }

    }

    private String getAdopterName(int adopterId) {
        Adopter adopter = AdoptersDAO.getAdopterById(adopterId);
        if (adopter != null) {
            return adopter.getName();
        }
        return "Unknown";
    }

    private int getAdopterId() {
        Integer selectedId = (Integer)this.adopterIdComboBox.getValue();
        if (selectedId != null) {
            return selectedId;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Adopter Selected");
            alert.setHeaderText((String)null);
            alert.setContentText("Please select an adopter from the list.");
            alert.showAndWait();
            return -1;
        }
    }

    @FXML
    public void showAddPetDialog() {
        Dialog<ButtonType> dialog = new Dialog();
        dialog.setTitle("Add Found Pet");
        dialog.setHeaderText("Enter pet details:");
        VBox dialogVBox = new VBox(10.0);
        TextField nameField = new TextField();
        TextField breedField = new TextField();
        ComboBox<String> typeComboBox = new ComboBox();
        typeComboBox.getItems().addAll(new String[]{"Cat", "Dog", "Bird", "Other"});
        TextField ageField = new TextField();
        TextArea descriptionArea = new TextArea();
        dialogVBox.getChildren().addAll(new Node[]{new Label("Name:"), nameField, new Label("Breed:"), breedField, new Label("Type:"), typeComboBox, new Label("Age:"), ageField, new Label("Description:"), descriptionArea});
        dialog.getDialogPane().setContent(dialogVBox);
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(new ButtonType[]{okButton, cancelButton});
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            String name = nameField.getText().trim();
            String breed = breedField.getText().trim();
            String type = (String)typeComboBox.getValue();
            int age = Integer.parseInt(ageField.getText().trim());
            String description = descriptionArea.getText().trim();
            if (!name.isEmpty() && !breed.isEmpty() && type != null && age > 0) {
                Pet newPet = new Pet(0, name, breed, type, age, description);
                this.petDAO.addPet(newPet);
                this.loadPets();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText((String)null);
                alert.setContentText("Pet added successfully!");
                alert.showAndWait();
            }
        }

    }

    @FXML
    private void handleViewAdopters() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AdoptersView.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage)this.viewAdoptersButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Adopters List");
            stage.show();
        } catch (IOException var5) {
            IOException e = var5;
            e.printStackTrace();
        }

    }

    @FXML
    public void addAdopter() {
        String adopterName = this.addAdopterNameField.getText().trim();
        String adopterContact = this.addAdopterContactField.getText().trim();
        if (!adopterName.isEmpty() && !adopterContact.isEmpty()) {
            Adopter newAdopter = new Adopter(0, adopterName, adopterContact);
            AdoptersDAO adopterDAO = new AdoptersDAO();
            int adopterId = adopterDAO.addAdopter(newAdopter);
            Alert alert;
            if (adopterId != -1) {
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Adopter Added");
                alert.setHeaderText((String)null);
                alert.setContentText("The adopter has been successfully added.");
                alert.showAndWait();
                this.addAdopterNameField.clear();
                this.addAdopterContactField.clear();
            } else {
                alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText((String)null);
                alert.setContentText("There was an issue adding the adopter.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Incomplete Information");
            alert.setHeaderText((String)null);
            alert.setContentText("Please fill in the adopter's details.");
            alert.showAndWait();
        }

    }

    @FXML
    public void deleteButton(ActionEvent actionEvent) {
        Pet selectedPet = (Pet)this.petsTableView.getSelectionModel().getSelectedItem();
        Alert alert;
        if (selectedPet != null) {
            this.petDAO.removePet(selectedPet);
            this.petsTableView.getItems().remove(selectedPet);
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Pet Removed");
            alert.setHeaderText((String)null);
            alert.setContentText("The pet has been removed from the system.");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Pet Selected");
            alert.setHeaderText((String)null);
            alert.setContentText("Please select a pet to delete.");
            alert.showAndWait();
        }

    }
}
