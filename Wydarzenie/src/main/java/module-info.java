module com.example.wydarzenie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.wydarzenie to javafx.fxml;
    exports com.example.wydarzenie;
}