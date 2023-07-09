package com.example.wydarzenie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class RejestracjaCtr {
    @FXML
    private TextField txtImie;
    @FXML
    private TextField txtNazwisko;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtPass2;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnRejestruj;

    @FXML
    protected void onbtnRejestrujClick() throws SQLException {
        boolean jestOK =true;
        String sqlQuery = "SELECT login FROM users";
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        ObservableList<String> user=FXCollections.observableArrayList();
        try  (Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
              PreparedStatement ps = conn.prepareStatement(sqlQuery);
              ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String login = rs.getString("login");
                if(login.equals(txtLogin.getText().toString())) jestOK=false;
                System.out.println(" login;   "+login);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if(jestOK==false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat Bazy Danych");
            alert.setHeaderText("Taki użytkownik już istnieje.");
            alert.setContentText("Wpisz inną nazwę użytkownika");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });
        }
        else {
            if(txtPass.getText().toString().equals(txtPass2.getText().toString())) {

                sqlQuery = "INSERT INTO users (imie,nazwisko,login,haslo,email) VALUES('"+txtImie.getText().toString()+
                        "', '"+txtNazwisko.getText().toString()+"', '"+txtLogin.getText().toString()+"', '"+
                        txtPass.getText().toString()+"', '"+txtEmail.getText().toString()+"')";
                System.out.println(sqlQuery);
                connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
                Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
                Statement ps = (Statement) conn.createStatement();
                ps.execute(sqlQuery);
                System.out.println("Zapisano nowego usera do bazy");

            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Walidacja wpisanych haseł ");
                alert.setHeaderText("Wpisane hasła w obu polach muszą być jednakowe");
                alert.setContentText("Sprawdź poprawność tych haseł");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                    }
                });
            }
        }
    }

}

