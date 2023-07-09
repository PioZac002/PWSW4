package com.example.wydarzenie;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class panelAdminCtr implements Initializable {
    public String aktRowUsers;
    public String nazwaEvent;
    public String agendaEvent;
    public String dataEvent;
    public int idEvent;
    public int idUsers;
    public int idLista;

    @FXML
    private Button acceptudzial;
    @FXML
    private Button deleteUdzial;
    @FXML
    private Button dodajEvent;
    @FXML
    private Button dodajUseraButton;
    @FXML
    private Button usunUseraButton;
    @FXML
    private TableView<User> users;
    @FXML
    private TableColumn<User, String> imie;
    @FXML
    private TableColumn<User, String> nazwisko;
    @FXML
    private TableColumn<User, String> login;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableView<Event> Events;
    @FXML
    private TableColumn<Event, String> nazwa;
    @FXML
    private TableColumn<Event, String> termin;
    @FXML
    private TableColumn<Event, String> agenda;
    @FXML
    private TableView<Lista> lista;
    @FXML
    private TableColumn<Lista, String> imie2;
    @FXML
    private TableColumn<Lista, String> nazwisko2;
    @FXML
    private TableColumn<Lista, String> typ;
    @FXML
    private TableColumn<Lista, String> posilek;
    @FXML
    private TableColumn<Lista, String> akcept;
    @FXML
    private TableColumn<Lista, String> id;
    @FXML
    private Button modyfikujEvent;
    @FXML
    private Button resethaslo;
    @FXML
    private Button usunEvent;
    @FXML
    private Button RbtnRejestruj;
    @FXML
    private Button RbtnAnuluj;
    @FXML
    private Label opis;
    @FXML
    private Pane frame;
    @FXML
    private Pane panel2;
    @FXML
    private TextField RtxtImie;
    @FXML
    private TextField RtxtNazwisko;
    @FXML
    private TextField RtxtLogin;
    @FXML
    private TextField RtxtEmail;
    @FXML
    private TextField RtxtPass;
    @FXML
    private TextField RtxtPass2;
    @FXML
    private Pane paneH;
    @FXML
    private Pane paneE;
    @FXML
    private Pane paneA;
    @FXML
    private TextField txtNPass;
    @FXML
    private Label labelEvent;
    @FXML
    private Button paneEZapisz;
    @FXML
    private Button PaneEAnuluj;
    @FXML
    private TextField paneENazwa;
    @FXML
    private TextField paneEData;
    @FXML
    private TextArea paneEAgenda;
    @FXML
    private TextArea textAE;
    @FXML
    private Label zapisTyp;
    @FXML
    private Label zapisPosilek;
    @FXML
    private ImageView Image1;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // zestaw nazwanych komponentów + nazwanych kolumn w tabelach
        panel2.getStyleClass().add("panel2");
        imie.setCellValueFactory(new PropertyValueFactory<User, String>("imie"));
        nazwisko.setCellValueFactory(new PropertyValueFactory<User, String>("nazwisko"));
        login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        imie2.setCellValueFactory(new PropertyValueFactory<Lista, String>("imie"));
        nazwisko2.setCellValueFactory(new PropertyValueFactory<Lista, String>("nazwisko"));
        typ.setCellValueFactory(new PropertyValueFactory<Lista, String>("typ"));
        posilek.setCellValueFactory(new PropertyValueFactory<Lista, String>("posilek"));
        akcept.setCellValueFactory(new PropertyValueFactory<Lista, String>("akcept"));
        nazwa.setCellValueFactory(new PropertyValueFactory<Event, String>("nazwa"));
        termin.setCellValueFactory(new PropertyValueFactory<Event, String>("termin"));
        // wczytanie rekordów z bazy danych do widoku tabelki users
        ObservableList<User> u = wczytajUsers("SELECT * FROM users");
        users.setItems(u); // do tabView wczytujemy dane
        // wczytanie rekordów z bazy danych do widoku tablelki events
        ObservableList<Event> e = wczytajEvents("Select * from events");
        Events.setItems(e);
        // wczytywanie z bazy powiazanych rekordów do widoku tabeliki lista (nie wybrno wydarzenia, zatem na tym etapie wczytujemy pierwszy rekord dla id_events=1)
        ObservableList<Lista> list = wczytajListe("select mk.imie, mk.nazwisko,z.typ,z.posilek,z.akcept,z.id_events, z.id from users as mk inner join zgloszenia as z ON mk.id=z.id_users where z.id_events=1");
        lista.setItems(list);
    }
    public static class User {
        private int id;
        private String imie;
        private String nazwisko;
        private String login;
        private String haslo;
        private String email;
        public User() { }
        public User(int id, String imie, String nazwisko, String login, String email) {
            this.id = id;
            this.imie = imie;
            this.nazwisko = nazwisko;
            this.login = login;
            this.email = email;
        }
        public int getId() {return id;}
        public String getImie() {
            return imie;
        }
        public String getNazwisko() {
            return nazwisko;
        }
        public String getLogin() {
            return login;
        }
        public String getEmail() {
            return email;
        }
    }
    public class Event {
        private int id;
        private String nazwa;
        private String termin;
        private String agenda;
        public Event(){}
        public Event(int id, String nazwa, String agenda, String termin) {
            this.id = id;
            this.nazwa = nazwa;
            this.agenda = agenda;
            this.termin = termin;
        }
        public int getId() { return id;}
        public String getNazwa() {
            return nazwa;
        }
        public String getAgenda() {
            return agenda;
        }
        public String getTermin() {
            return termin;
        }
    }
    public class Lista {
        private int id;
        private String imie;
        private String nazwisko;
        private String typ;
        private String posilek;
        private String akcept;
        public Lista() {}
        public Lista(int id, String imie, String nazwisko, String typ, String posilek, String akcept) {
            this.id = id;
            this.imie = imie;
            this.nazwisko = nazwisko;
            this.typ = typ;
            this.posilek = posilek;
            this.akcept = akcept;
        }
        public int getId() { return id;}
        public String getImie() { return imie;}
        public String getNazwisko() { return nazwisko;}
        public String getTyp() { return typ;}
        public String getPosilek() { return posilek; }
        public String getAkcept() { return akcept;}
    }
    // --- zestaw funkcji zwiazanych z obsługą tablicy users
    @FXML
    protected void onTableUsersClick() throws IOException {
        resethaslo.setDisable(false);
        usunUseraButton.setDisable(false);
        usunEvent.setDisable(true);
        modyfikujEvent.setDisable(true);
        acceptudzial.setDisable(true);
        deleteUdzial.setDisable(true);
        if (users.getSelectionModel().getSelectedItem() != null) {
            User selectedPerson = users.getSelectionModel().getSelectedItem();
            aktRowUsers = selectedPerson.getLogin();
            System.out.println("Wybrano login  " + aktRowUsers);
            resethaslo.setDisable(false);
            usunUseraButton.setDisable(false);
            paneA.setVisible(false);
        }
    }
    @FXML
    protected void ondodajUseraButtonClick() throws IOException {
        panel2.setVisible(true);
    }
    @FXML
    protected void onusunUseraButtonClick() throws IOException {
        if(aktRowUsers.equals("admin")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Komunikat Bazy danych ");
            alert.setHeaderText("Uwaga !!! Użytkownik admin nie moze być usunięty z bazy.");
            alert.showAndWait().ifPresent(rw -> {
                if(rw == ButtonType.OK) {
                }
            });
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Komunikat Bazy danych ");
            alert.setHeaderText("Uwaga !!! Zazanczony rekord będzie usunięty.");
            alert.setContentText("Czy jesteś pewnien, że chcesz to zrobić?");
            alert.showAndWait().ifPresent(rw -> {
                if (rw == ButtonType.OK) {
                    String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
                    try {
                        // pobieram id z zaznaczonego rekordu w tabelce  i wczytuje do zmiennej idUsers
                        Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
                        PreparedStatement psId = conn.prepareStatement("SELECT id from users WHERE login =?");
                        psId.setString(1, aktRowUsers);
                        ResultSet rs = psId.executeQuery();
                        while (rs.next()) {
                            idUsers = rs.getInt("id");
                        }
                        // usuwam zaznaczony wskazany rekord z tablicy users
                        PreparedStatement ps = conn.prepareStatement("DELETE from users WHERE login =?");
                        ps.setString(1, aktRowUsers);
                        ps.executeUpdate();
                        // usuwam powiązane rekordy z tablicy zgłoszenia (te dla których id_users = idUsers)
                        PreparedStatement psZ = conn.prepareStatement("DELETE from zgloszenia WHERE id_users = idUsers");
                        psZ.executeUpdate();
                    } catch (SQLException e) {
                    }
                }
            });

            refresh();
        }
    }
    @FXML
    protected void onuresethasloButtonClick() throws IOException {
        paneH.setVisible(true);
        System.out.println("Do zresetowania hasła wyznaczony user o loginie: " + aktRowUsers);
    }
    protected ObservableList<User> wczytajUsers(String sql) { //"SELECT * FROM users";
        String query = sql;
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        ObservableList<User> user = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id=rs.getInt("id");
                String name = rs.getString("imie");
                String lastName = rs.getString("nazwisko");
                String login = rs.getString("login");
                String email = rs.getString("email");
                user.add(new User(id, name, lastName, login, email));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    @FXML
    protected void RbtnAnulujClick() { panel2.setVisible(false);}
    @FXML
    protected void onRbtnRejestrujClick() throws SQLException {

        boolean jestOK = true;
        String sqlQuery = "SELECT login FROM users";
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        ObservableList<String> user = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
             PreparedStatement ps = conn.prepareStatement(sqlQuery);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String login = rs.getString("login");
                if (login.equals(RtxtLogin.getText().toString())) jestOK = false;
                System.out.println(" login;   " + login);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (jestOK == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Komunikat Bazy Danych");
            alert.setHeaderText("Taki użytkownik już istnieje.");
            alert.setContentText("Wpisz inną nazwę użytkownika");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });
        } else {
            if (RtxtPass.getText().toString().equals(RtxtPass2.getText().toString())) {
                //zapisywanie nowego uzytkownika do bazy
                sqlQuery = "INSERT INTO users (imie,nazwisko,login,haslo,email) VALUES('" + RtxtImie.getText().toString() +
                        "', '" + RtxtNazwisko.getText().toString() + "', '" + RtxtLogin.getText().toString() + "', '" +
                        RtxtPass.getText().toString() + "', '" + RtxtEmail.getText().toString() + "')";
                System.out.println(sqlQuery);
                connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
                Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
                Statement ps = (Statement) conn.createStatement();
                ps.execute(sqlQuery);
                System.out.println("Zapisano nowego usera do bazy");
            } else {
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
        panel2.setVisible(false);
        refresh();
    }
    @FXML
    protected void onbtnNZapiszClick() {
        System.out.println("Do zresetowania hasła wyznaczony user o loginie: " + aktRowUsers);
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        try {
            Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
            PreparedStatement ps = conn.prepareStatement("Update users  set haslo = \"" + txtNPass.getText().toString() + "\" WHERE login =?");
            ps.setString(1, aktRowUsers);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
        paneH.setVisible(false);
        refresh();
    }
    // --- zestaw funkcji związanych z obsługą tablicy events
    @FXML
    protected void onTableEventsClick() {
        resethaslo.setDisable(true);
        usunUseraButton.setDisable(true);
        usunEvent.setDisable(false);
        modyfikujEvent.setDisable(false);
        acceptudzial.setDisable(true);
        deleteUdzial.setDisable(true);
        if (Events.getSelectionModel().getSelectedItem() != null) {
            Event selectedPerson = Events.getSelectionModel().getSelectedItem();
            idEvent = selectedPerson.getId();
            nazwaEvent = selectedPerson.getNazwa();
            agendaEvent = selectedPerson.getAgenda();
            dataEvent = selectedPerson.getTermin();
            textAE.setText(agendaEvent);
            mySQL.select = nazwaEvent;
            mySQL.execute = agendaEvent;
            mySQL.param = dataEvent;
            opis.setText(nazwaEvent + ".  Lista uczestników:");
            System.out.println("Wybrano wydarzenie: " + nazwaEvent + "  o ID= " + idEvent);
            ObservableList<Lista> list = wczytajListe("select mk.imie, mk.nazwisko,z.typ,z.posilek,z.akcept,z.id_events, z.id from users as mk inner join zgloszenia as z ON mk.id=z.id_users where z.id_events='" + idEvent + "'");// = \""+nazwaEvent+"\") )");
            lista.setItems(list);
            usunEvent.setDisable(false);
            modyfikujEvent.setDisable(false);
            paneA.setVisible(true);
        }
    }
    @FXML
    protected void ondodajEventClick() throws IOException {
        paneE.setVisible(true);
        paneENazwa.setText("");
        paneEAgenda.setText("");
        paneEData.setText("");
        mySQL.czyDopis = true;
    }
    @FXML
    protected void onUsunEventClick() {
        System.out.println("Tu usuniemy zapis: " + nazwaEvent + " o id = "+ idEvent +" z bazy MYSQL");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Komunikat Bazy Danych ");
        alert.setHeaderText("UWAGA !!! zaznaczony rekord będzie usunięty.");
        alert.setContentText("Cz jesteś pewnień, że chcesz to zrobić? ");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
                try {
                    Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
                    // usuwam powiązane rekordy z tablicy zgłoszenia (te dla których id_event = idEvent)
                    PreparedStatement psZ = conn.prepareStatement("DELETE from zgloszenia WHERE id_events = ?");
                    psZ.setString(1, String.valueOf(idEvent));
                    psZ.executeUpdate();
                    // usuwam zaznaczony rekord z tablicy events
                    conn = DriverManager.getConnection(connectionUrl, "root", "");
                    PreparedStatement ps = conn.prepareStatement("DELETE from events WHERE nazwa =?");
                    ps.setString(1, mySQL.select);
                    ps.executeUpdate();

                } catch (SQLException e) { }
            }
        });

        refresh();
    }
    @FXML
    protected void onmodyfikujEventClick() throws IOException {
        mySQL.czyDopis = false;
        paneE.setVisible(true);
        mySQL.select = nazwaEvent;
        mySQL.execute = agendaEvent;
        mySQL.param = dataEvent;
        paneENazwa.setText(mySQL.select);
        paneEAgenda.setText(mySQL.execute);
        paneEData.setText(mySQL.param);
    }
    protected ObservableList<Event> wczytajEvents(String sql) {
        String query = sql;
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        ObservableList<Event> iv = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nazwa = rs.getString("nazwa");
                String agenda = rs.getString("agenda");
                String data = rs.getString("data");
                iv.add(new Event(id, nazwa, agenda, data));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return iv;
    }
    @FXML
    protected void onpaneEZapiszClick() throws SQLException {
        if (mySQL.czyDopis) {  //zapis nowego rekordu to tablicy events
            if (paneENazwa.getLength() * paneEAgenda.getLength() * paneEData.getLength() > 0) {
                String sqlQuery = "INSERT INTO events (nazwa,agenda,data) VALUES('" + paneENazwa.getText().toString() +
                        "', '" + paneEAgenda.getText().toString() + "', '" + paneEData.getText().toString() + "')";
                System.out.println(sqlQuery);
                String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
                Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
                Statement ps = (Statement) conn.createStatement();
                ps.execute(sqlQuery);
                System.out.println("Zapisano nowe wydarzenie do bazy");
                refresh();
                paneE.setVisible(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Walidacja danych ");
                alert.setHeaderText("Wszystkie pola muszą być wypełnione. Data w formacie RRRR.MM.DD");
                alert.setContentText("Wypełnij pola i sprawdź poprawność zapisanej daty");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                    }
                });
            }
        } else {
            String sqlQuery = "Update events  set nazwa = \"" + paneENazwa.getText().toString() + "\"" +
                    ", agenda = \"" + paneEAgenda.getText().toString() + "\"" +
                    ", data = \"" + paneEData.getText().toString() + "\"" + " WHERE nazwa = \"" + paneENazwa.getText().toString() + "\"";
            System.out.println(sqlQuery);
            String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
            Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
            Statement ps = (Statement) conn.createStatement();
            ps.execute(sqlQuery);
            System.out.println("Zmodyfikowano rekord");
            refresh();
            paneE.setVisible(false);
        }
    }
    @FXML
    protected void onpaneEAnulujClick() {
        paneE.setVisible(false);
    }
    // --- zestaw funkcji związanych z obsługą tablicy zgloszenia
    @FXML
    protected void onTableListaClick() throws IOException {
        resethaslo.setDisable(true);
        usunUseraButton.setDisable(true);
        usunEvent.setDisable(true);
        modyfikujEvent.setDisable(true);
        acceptudzial.setDisable(false);
        deleteUdzial.setDisable(false);
        if (lista.getSelectionModel().getSelectedItem() != null) {
            Lista selectedPerson = lista.getSelectionModel().getSelectedItem();
            idLista = selectedPerson.getId();
            acceptudzial.setDisable(false);
            deleteUdzial.setDisable(false);
            System.out.println("Wybrano Id  " + idLista);
        }
    }
    @FXML
    protected void onacceptudzialClick() throws IOException {
        System.out.println("Potwierdzenie zgłoszenia na wydarzenie, zapis rekordu o id = " + idLista);
        if (lista.getSelectionModel().getSelectedItem() != null) {
            Lista selectedPerson = lista.getSelectionModel().getSelectedItem();
            idLista = selectedPerson.getId();
            System.out.println("Wybrano id  " + idLista);
            String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
            try {
                Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
                PreparedStatement ps = conn.prepareStatement("Update zgloszenia  set akcept = 'yes' WHERE id = " + idLista + " ");
                ps.executeUpdate();
            } catch (SQLException e) {
            }
            paneA.setVisible(false);
            refresh();
        }
    }
    @FXML
    protected void ondeleteudzialClick() throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Komunikat Bazy Danych");
        alert.setHeaderText("Uwaga !!! Zaznaczony rekord będzie usuniety z bazy.");
        alert.setContentText("Czy jesteś pewien, że chcesz to zrobić? ");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                if (lista.getSelectionModel().getSelectedItem() != null) {
                    Lista selectedPerson = lista.getSelectionModel().getSelectedItem();
                    idLista = selectedPerson.getId();
                    System.out.println("Wybrano id  " + idLista);
                    String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
                    try {
                        Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
                        PreparedStatement ps = conn.prepareStatement("delete from zgloszenia  WHERE id = " + idLista + " ");
                        ps.executeUpdate();
                    } catch (SQLException e) {
                    }
                    paneA.setVisible(false);
                    wczytajEvents("select * from events");
                    refresh();
                }
            }
        });
        System.out.println("usuwamy rekord z tablicy zgloszenia");

    }
    protected ObservableList<Lista> wczytajListe(String sql) {
        String query = sql;
        String connectionUrl = "jdbc:mysql://localhost:3307/wydarzenia?serverTimezone=UTC&useSSL=false";
        ObservableList<Lista> iv = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "");
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String imie = rs.getString("imie");
                String nazwisko = rs.getString("nazwisko");
                String typ = rs.getString("typ");
                String posilek = rs.getString("posilek");
                String akcept = rs.getString("akcept");
                iv.add(new Lista(id, imie, nazwisko, typ, posilek, akcept));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return iv;
    }
    // --- funkcje związane z obsługą odświeżania danych w tablicach ----------------
    @FXML
    protected void onframeClick() throws IOException {
        ObservableList<User> u = wczytajUsers("SELECT * FROM users");
        users.setItems(u);
        // wczytanie z bazy danych do tablicy events
        ObservableList<Event> e = wczytajEvents("Select * from events");
        Events.setItems(e);
        paneA.setVisible(false);
        paneH.setVisible(false);
        paneE.setVisible(false);
        panel2.setVisible(false);
        refresh();
    }
    @FXML
    protected void refresh() {
        ObservableList<User> u = wczytajUsers("SELECT * FROM users");
        users.setItems(u);
        // wczytanie z bazy danych do tablicy events
        ObservableList<Event> e = wczytajEvents("Select * from events");
        Events.setItems(e);
        ObservableList<Lista> list = wczytajListe("select mk.imie, mk.nazwisko,z.typ,z.posilek,z.akcept,z.id_events, z.id from users as mk inner join zgloszenia as z ON mk.id=z.id_users where z.id_events=\'" + idEvent + "\'");// = \""+nazwaEvent+"\") )");
        lista.setItems(list);
        paneA.setVisible(false);
        resethaslo.setDisable(true);
        usunUseraButton.setDisable(true);
        usunEvent.setDisable(true);
        modyfikujEvent.setDisable(true);
        acceptudzial.setDisable(true);
        deleteUdzial.setDisable(true);
        System.out.println("wykonano refresh");
    }
}

