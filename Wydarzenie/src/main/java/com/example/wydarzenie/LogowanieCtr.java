package com.example.wydarzenie;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class LogowanieCtr  {
    @FXML
    private TextField txtLogin;
    @FXML
    private CheckBox chkPokaz;
    @FXML
    private TextField txtPokaz;
    @FXML
    private PasswordField passField;
    @FXML
    private Button btnZaloguj;
    @FXML
    protected void onbtnZalogujClick() throws IOException {
        mySQL.login=txtLogin.getText().toString();
        mySQL.passwd=passField.getText().toString();
        boolean check =checkSQL();
        System.out.println("sprawdzenie ="+check);
        if(check) {
            if(mySQL.login.equals("admin")) {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(WydarzenieApp.class.getResource("panelAdmin.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 830, 630);
                stage.setTitle("Panel Administratora");
                stage.setScene(scene);
                stage.show();
            }
            else{
                //czytamy id usera z bazy SQL
                System.out.println("ID usera ="+mySQL.idUser);
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(WydarzenieApp.class.getResource("zapis.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 330, 415);
                stage.setTitle("user: "+mySQL.login);
                stage.setScene(scene);
                stage.show();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat Bazy Danych");
            alert.setHeaderText("Niepoprawny login lub hasło");
            alert.setContentText("Sprawdź poprawność wpisanych danych.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });
        }
    }
    @FXML
    protected void ontxtPokazClick() {
        txtPokaz.setVisible(false);
        chkPokaz.setSelected(false);
    }
    @FXML
    protected void onchkPokazClick() {
        if(chkPokaz.isSelected()) {txtPokaz.setVisible(true);txtPokaz.setText(passField.getText());}
        else {txtPokaz.setVisible(false);txtPokaz.setText("");}
    }
    @FXML
    protected void onnowyLabelClick() throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(WydarzenieApp.class.getResource("rejestracja.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 260);
        stage.setTitle("Rejestracja uzytkowników");
        stage.setScene(scene);
        stage.show();
    }
    public boolean checkSQL(){
        boolean result=false;
        String query = "select * from users";
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        try  (Connection conn = DriverManager.getConnection(connectionUrl, "root", "-----");
              PreparedStatement ps = conn.prepareStatement(query);
              ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int rsId=rs.getInt(1);
                String imie=rs.getString(2);
                String nazwisko=rs.getString(3);
                String login = rs.getString(4).toString();
                String passwd = rs.getString(5).toString();
                String email = rs.getString(6);
                if(login.equals(mySQL.login) && passwd.equals(mySQL.passwd)) {result=true;mySQL.idUser=rsId;}
            }
        } catch (SQLException ex) {ex.printStackTrace(); }
    return result;
    }
   public void checkKey(){
         passField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                try {
                    onbtnZalogujClick();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
   }
}