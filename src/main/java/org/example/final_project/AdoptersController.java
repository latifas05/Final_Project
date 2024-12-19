    package org.example.final_project;


    import java.io.IOException;
    import java.util.List;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.TableView;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.stage.Stage;
    import org.example.final_project.Model.Adopter;
    import org.example.final_project.DAO.AdoptersDAO;

    public class AdoptersController {
        @FXML
        private TableView<Adopter> adoptersTableView;
        @FXML
        private TableColumn<Adopter, Integer> idColumn;
        @FXML
        private TableColumn<Adopter, String> nameColumn;
        @FXML
        private TableColumn<Adopter, String> contactColumn;
        @FXML
        private Button goToHomeButton;
        private ObservableList<Adopter> adoptersList;
        private AdoptersDAO adoptersDAO;

        public AdoptersController() {
        }

        @FXML
        public void initialize() {
            this.adoptersList = FXCollections.observableArrayList();
            this.adoptersDAO = new AdoptersDAO();
            this.loadAdopters();
            this.adoptersTableView.setItems(this.adoptersList);
            this.idColumn.setCellValueFactory(new PropertyValueFactory("id"));
            this.nameColumn.setCellValueFactory((cellData) -> {
                return ((Adopter)cellData.getValue()).nameProperty();
            });
            this.contactColumn.setCellValueFactory((cellData) -> {
                return ((Adopter)cellData.getValue()).contactProperty();
            });
        }

        private void loadAdopters() {
            List<Adopter> adopters = this.adoptersDAO.getAllAdopters();
            this.adoptersList.addAll(adopters);
        }

        @FXML
        private void handleGoToHome() {
            try {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("hello-view.fxml"));
                Parent root = (Parent)loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage)this.goToHomeButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Home");
                stage.show();
            } catch (IOException var5) {
                IOException e = var5;
                e.printStackTrace();
            }

        }
    }

