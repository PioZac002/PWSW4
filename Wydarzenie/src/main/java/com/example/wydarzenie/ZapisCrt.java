package com.example.wydarzenie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ZapisCrt implements Initializable{
    ObservableList<String> typ = FXCollections.observableArrayList("Słuchacz", "Autor", "Sponsor", "Organizator");
    ObservableList<String> posilek = FXCollections.observableArrayList("bez posiłku", "bez preferencji", "wegetariański", "bez glutenu");
    ObservableList<String> nazwa1 = FXCollections.observableArrayList();
    @FXML
    private Button btnZapisz;
    @FXML
    private ChoiceBox typChoiceBox;
    @FXML
    private TextArea txtAgenda;
    @FXML
    private TextField txtData;
    @FXML
    private ChoiceBox posilekChoiceBox;
    @FXML
    private ComboBox nazwaComboBox;
    @FXML
    private void odczytAgendy(){
        String temat=nazwaComboBox.getValue().toString();
        System.out.println("odczyt wlasciwej Agendy dla : "+temat);
        String sqlSelectAllPersons = "SELECT * FROM events WHERE nazwa = \'"+temat+"\'";
        System.out.println(sqlSelectAllPersons);
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        try  (Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
              PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
              ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id=rs.getInt("id");
                mySQL.idEvent=id;
                String agenda = rs.getString("agenda");
                String data=rs.getString("data");
                System.out.println(agenda);
                txtAgenda.setText(agenda);
                txtData.setText(data);
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    protected void onbtnZapiszClick() throws IOException, SQLException {
        String sqlQuery = "INSERT INTO zgloszenia (typ,posilek,id_users,id_events) VALUES('"+typChoiceBox.getValue().toString()+
            "', '"+posilekChoiceBox.getValue().toString()+"', "+mySQL.idUser+", "+mySQL.idEvent+") ";
        System.out.println(sqlQuery);
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
        Statement ps = (Statement) conn.createStatement();
        ps.execute(sqlQuery);
        System.out.println("Zapisano dane  do tablicy zgloszenia");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typChoiceBox.setItems(typ);
        posilekChoiceBox.setItems(posilek);
        String sqlSelectAllPersons = "SELECT * FROM events";
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        try  (Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
              PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
              ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nazwa = rs.getString("nazwa");
                nazwa1.add(nazwa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        nazwaComboBox.setItems(nazwa1);
        nazwaComboBox.getSelectionModel().select(0);
        odczytAgendy();
    }
}
