module org.example.final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.final_project to javafx.fxml;
    exports org.example.final_project;
    exports org.example.final_project.Model;
    opens org.example.final_project.Model to javafx.fxml;
    exports org.example.final_project.DAO;
    opens org.example.final_project.DAO to javafx.fxml;
}