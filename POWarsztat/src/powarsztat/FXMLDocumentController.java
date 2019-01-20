/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package powarsztat;

import hibernate.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Maciek
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ListView<Klient> listaKlient;
    @FXML
    private Button Dodaj_k;
    @FXML
    private Button Usun_k;
    @FXML
    private TextField ki;
    @FXML
    private TextField kn;
    @FXML
    private TextField kt;
    @FXML
    private TextField ka;
    @FXML
    private ChoiceBox<Klient> choiceKlient;
    @FXML
    private ChoiceBox<Samochod> choiceSamochod;
    @FXML
    private Button pobierzk;
    @FXML
    private Button okk;
    @FXML
    private ListView<Samochod> listaSamochod;
    @FXML
    private Button Dodaj_s;
    @FXML
    private Button Usun_s;
    @FXML
    private TextField sMarka;
    @FXML
    private TextField sModel;
    @FXML
    private TextField sNr;
    @FXML
    private TextField sVin;
    @FXML
    private Button pobierzs;
    @FXML
    private Button oks;
    @FXML
    private ListView<Naprawa> listaNaprawa;
    @FXML
    private Button Dodaj_n;
    @FXML
    private Button Usun_n;
    @FXML
    private TextField nKoszt;
    @FXML
    private TextField nUsterka;
    @FXML
    private Button pobierzn;
    @FXML
    private Button okn;
    @FXML
    private TableView<Klient> tableKlient;
    @FXML
    private TableView<Samochod> tableSamochod;
    @FXML
    private TableView<Naprawa> tableNaprawa;
    @FXML
    private TextField filtrk;
    @FXML
    private TextField filtrn;
    @FXML
    private TextField filtrs;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Configuration configuration1
                = new Configuration().configure("hibernate.cfg.xml");

        configuration1.addAnnotatedClass(Klient.class);
        configuration1.addAnnotatedClass(Naprawa.class);
        configuration1.addAnnotatedClass(Samochod.class);
        ServiceRegistry serviceRegistry1 = new StandardServiceRegistryBuilder()
                .applySettings(configuration1.getProperties()).build();

        SessionFactory factory1 = configuration1.buildSessionFactory(serviceRegistry1);

        Session session1 = factory1.openSession();

        List<Klient> list2 = session1.createCriteria(Klient.class).list();
        ObservableList<Klient> getKlient = FXCollections.observableArrayList(list2);

        TableColumn<Klient, String> imieColumn = new TableColumn<>("Imie");
        imieColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("imie"));

        TableColumn<Klient, String> nazwiskoColumn = new TableColumn<>("Nazwisko");
        nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("Nazwisko"));

        TableColumn<Klient, String> nrtelColumn = new TableColumn<>("Nr_tel");
        nrtelColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("Nr_tel"));

        TableColumn<Klient, String> adresColumn = new TableColumn<>("Adres");
        adresColumn.setCellValueFactory(new PropertyValueFactory<Klient, String>("adres"));

        tableKlient.setItems(getKlient);
        tableKlient.getColumns().addAll(imieColumn, nazwiskoColumn, nrtelColumn, adresColumn);

        List<Klient> listaKlientow = session1.createCriteria(Klient.class).list();
        choiceKlient.getItems().addAll(listaKlientow);

        FilteredList<Klient> filteredKlient = new FilteredList<>(getKlient, p -> true);

        List<Samochod> list3 = session1.createCriteria(Samochod.class).list();
        ObservableList<Samochod> getSamochod = FXCollections.observableArrayList(list3);

        TableColumn<Samochod, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(new PropertyValueFactory<Samochod, String>("model"));

        TableColumn<Samochod, String> markaColumn = new TableColumn<>("Marka");
        markaColumn.setCellValueFactory(new PropertyValueFactory<Samochod, String>("marka"));

        TableColumn<Samochod, String> nrrejColumn = new TableColumn<>("Nr_rejestracyjny");
        nrrejColumn.setCellValueFactory(new PropertyValueFactory<Samochod, String>("nr_rejestracyjny"));

        TableColumn<Samochod, String> vinColumn = new TableColumn<>("VIN");
        vinColumn.setCellValueFactory(new PropertyValueFactory<Samochod, String>("VIN"));

        tableSamochod.setItems(getSamochod);
        tableSamochod.getColumns().addAll(modelColumn, markaColumn, nrrejColumn, vinColumn);

        List<Samochod> listaSamochodow = session1.createCriteria(Samochod.class).list();
        choiceSamochod.getItems().addAll(listaSamochodow);

        FilteredList<Samochod> filteredSamochod = new FilteredList<>(getSamochod, p -> true);

        List<Naprawa> list4 = session1.createCriteria(Naprawa.class).list();
        ObservableList<Naprawa> getNaprawa = FXCollections.observableArrayList(list4);

        TableColumn<Naprawa, String> kosztColumn = new TableColumn<>("Koszt");
        kosztColumn.setCellValueFactory(new PropertyValueFactory<Naprawa, String>("koszt"));

        TableColumn<Naprawa, String> usterkaColumn = new TableColumn<>("Usterka");
        usterkaColumn.setCellValueFactory(new PropertyValueFactory<Naprawa, String>("usterka"));

        TableColumn<Naprawa, String> imiekColumn = new TableColumn<>("Imie klienta");
        imiekColumn.setCellValueFactory(imiek -> new SimpleStringProperty(imiek.getValue().getKlient().getImie()));

        TableColumn<Naprawa, String> nazwiskokColumn = new TableColumn<>("Nazwisko klienta");
        nazwiskokColumn.setCellValueFactory(nazwiskok -> new SimpleStringProperty(nazwiskok.getValue().getKlient().getNazwisko()));

        TableColumn<Naprawa, String> nrrejsColumn = new TableColumn<>("Nr rejestracyjny");
        nrrejsColumn.setCellValueFactory(nrrejs -> new SimpleStringProperty(nrrejs.getValue().getSamochod().getNr_rejestracyjny()));

        tableNaprawa.setItems(getNaprawa);
        tableNaprawa.getColumns().addAll(kosztColumn, usterkaColumn, imiekColumn, nazwiskokColumn, nrrejsColumn);

        FilteredList<Naprawa> filteredNaprawa = new FilteredList<>(getNaprawa, p -> true);

        session1.close();
        factory1.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry1);

        // dodawanie klienta
        Dodaj_k.setOnAction((ActionEvent event) -> {
            if (ki.getText() != null && !ki.getText().isEmpty()
                    && kn.getText() != null && !kn.getText().isEmpty()
                    && kt.getText() != null && !kt.getText().isEmpty()
                    && ka.getText() != null && !ka.getText().isEmpty()) {
                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                List<Klient> list = session.createCriteria(Klient.class).list();
                tableKlient.getItems().addAll(list);

                Transaction transaction = session.beginTransaction();

                Klient nowy = new Klient();
                nowy.setImie(ki.getText());
                nowy.setNazwisko(kn.getText());
                nowy.setNr_tel(kt.getText());;
                nowy.setAdres(ka.getText());

                session.save(nowy);
                transaction.commit();
                tableKlient.getItems().clear();
                List<Klient> list1 = session.createCriteria(Klient.class).list();
                ObservableList<Klient> getKlient1 = FXCollections.observableArrayList(list1);

                tableKlient.getItems().addAll(list1);
                choiceKlient.getItems().clear();
                choiceKlient.getItems().addAll(list1);

                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);

                ki.clear();
                kn.clear();
                kt.clear();
                ka.clear();
            }

        });

        //usun klienta
        Usun_k.setOnAction((ActionEvent event) -> {
            if (tableKlient.getSelectionModel().getSelectedItem() != null) {
                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();

                Klient klient = new Klient();
                klient = tableKlient.getSelectionModel().getSelectedItem();
                int id = klient.getId_klienta();

                String usun = "delete from  Klient where  id_klienta=:id";

                Query query = session.createQuery(usun);

                query.setInteger("id", id);
                query.executeUpdate();

                transaction.commit();
                List<Klient> list1 = session.createCriteria(Klient.class).list();
                ObservableList<Klient> getKlient1 = FXCollections.observableArrayList(list1);
                choiceKlient.getItems().clear();
                choiceKlient.getItems().addAll(list1);
                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
                tableKlient.getItems().remove(klient);

            }

        });

        //Edycja Klienta
        pobierzk.setOnAction((ActionEvent event) -> {
            if (tableKlient.getSelectionModel().getSelectedItem() != null) {

                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();
                Klient klient = new Klient();
                klient = tableKlient.getSelectionModel().getSelectedItem();

                ki.setText(klient.getImie());
                kn.setText(klient.getNazwisko());
                kt.setText(klient.getNr_tel());;
                ka.setText(klient.getAdres());

                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
            }
        });

        okk.setOnAction((ActionEvent event) -> {
            if (ki.getText() != null && !ki.getText().isEmpty()
                    && kn.getText() != null && !kn.getText().isEmpty()
                    && kt.getText() != null && !kt.getText().isEmpty()
                    && ka.getText() != null && !ka.getText().isEmpty()) {
                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();
                Klient klient2 = new Klient();
                klient2 = tableKlient.getSelectionModel().getSelectedItem();
                int id = klient2.getId_klienta();
                String im = (kn.getText());
                String na = (kn.getText());
                String tel = (kt.getText());
                String ad = (ka.getText());

                String edytuj = "update Klient set imie=:im, nazwisko=:na, nr_tel=:tel, adres=:ad where id_klienta=:id";

                Query query = session.createQuery(edytuj);
                query.setInteger("id", id);
                query.setString("im", im);
                query.setString("na", na);
                query.setString("tel", tel);
                query.setString("ad", ad);
                query.executeUpdate();

                transaction.commit();
                tableKlient.getItems().clear();
                List<Klient> list1 = session.createCriteria(Klient.class).list();
                ObservableList<Klient> getKlient1 = FXCollections.observableArrayList(list1);

                tableKlient.getItems().addAll(list1);
                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);

                ki.clear();
                kn.clear();
                kt.clear();
                ka.clear();

            }

        });

        // dodawanie samochodu
        Dodaj_s.setOnAction((ActionEvent event) -> {
            if (sMarka.getText() != null && !sMarka.getText().isEmpty()
                    && sModel.getText() != null && !sModel.getText().isEmpty()
                    && sNr.getText() != null && !sNr.getText().isEmpty()
                    && sVin.getText() != null && !sVin.getText().isEmpty()) {
                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                List<Samochod> list = session.createCriteria(Samochod.class).list();
                tableSamochod.getItems().addAll(list);

                Transaction transaction = session.beginTransaction();

                Samochod nowy = new Samochod();
                nowy.setModel(sModel.getText());
                nowy.setMarka(sMarka.getText());
                nowy.setNr_rejestracyjny(sNr.getText());;
                nowy.setVIN(sVin.getText());

                session.save(nowy);
                transaction.commit();
                tableSamochod.getItems().clear();
                List<Samochod> list1 = session.createCriteria(Samochod.class).list();
                ObservableList<Samochod> getSamochod1 = FXCollections.observableArrayList(list1);

                tableSamochod.getItems().addAll(list1);
                choiceSamochod.getItems().clear();
                choiceSamochod.getItems().addAll(list1);

                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);

                sModel.clear();
                sMarka.clear();
                sNr.clear();
                sVin.clear();
            }

        });

        //usun samochod
        Usun_s.setOnAction((ActionEvent event) -> {
            if (tableSamochod.getSelectionModel().getSelectedItem() != null) {
                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();

                Samochod samochod = new Samochod();
                samochod = tableSamochod.getSelectionModel().getSelectedItem();
                int id = samochod.getId_samochodu();

                String usun = "delete from  Samochod where  id_samochodu=:id";

                Query query = session.createQuery(usun);

                query.setInteger("id", id);
                query.executeUpdate();

                transaction.commit();
                List<Samochod> list1 = session.createCriteria(Samochod.class).list();
                ObservableList<Samochod> getSamochod1 = FXCollections.observableArrayList(list1);
                choiceSamochod.getItems().clear();
                choiceSamochod.getItems().addAll(list1);
                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
                tableSamochod.getItems().remove(samochod);

            }

        });

        //Edycja samochodu
        pobierzs.setOnAction((ActionEvent event) -> {
            if (tableSamochod.getSelectionModel().getSelectedItem() != null) {

                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();
                Samochod samochod = new Samochod();
                samochod = tableSamochod.getSelectionModel().getSelectedItem();

                sModel.setText(samochod.getModel());
                sMarka.setText(samochod.getMarka());
                sNr.setText(samochod.getNr_rejestracyjny());;
                sVin.setText(samochod.getVIN());

                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
            }
        });

        oks.setOnAction((ActionEvent event) -> {
            if (sMarka.getText() != null && !sMarka.getText().isEmpty()
                    && sModel.getText() != null && !sModel.getText().isEmpty()
                    && sNr.getText() != null && !sNr.getText().isEmpty()
                    && sVin.getText() != null && !sVin.getText().isEmpty()) {
                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();
                Samochod samochod2 = new Samochod();
                samochod2 = tableSamochod.getSelectionModel().getSelectedItem();
                int id = samochod2.getId_samochodu();
                String marka = (sMarka.getText());
                String model = (sModel.getText());
                String nr = (sNr.getText());
                String vin = (sVin.getText());

                String edytuj = "update Samochod set model=:model, marka=:marka, nr_rej=:nr, VIN=:vin where id_samochodu=:id";

                Query query = session.createQuery(edytuj);
                query.setInteger("id", id);
                query.setString("model", model);
                query.setString("marka", marka);
                query.setString("nr", nr);
                query.setString("vin", vin);
                query.executeUpdate();

                transaction.commit();
                tableSamochod.getItems().clear();
                List<Samochod> list1 = session.createCriteria(Samochod.class).list();
                ObservableList<Samochod> getSamochod1 = FXCollections.observableArrayList(list1);

                tableSamochod.getItems().addAll(list1);
                choiceSamochod.getItems().clear();
                choiceSamochod.getItems().addAll(list1);
                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);

                sModel.clear();
                sMarka.clear();
                sNr.clear();
                sVin.clear();

            }

        });

        //Dodaj naprawe
        Dodaj_n.setOnAction((ActionEvent event) -> {
            if (nKoszt.getText() != null && !nKoszt.getText().isEmpty()
                    && nUsterka.getText() != null && !nUsterka.getText().isEmpty()) {

                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                List<Naprawa> list = session.createCriteria(Naprawa.class).list();
                tableNaprawa.getItems().addAll(list);

                Transaction transaction = session.beginTransaction();

                Naprawa nowy = new Naprawa();
                nowy.setKoszt((int) Float.parseFloat(nKoszt.getText()));
                nowy.setUsterka(nUsterka.getText());
                nowy.setKlient(choiceKlient.getValue());
                nowy.setSamochod(choiceSamochod.getValue());

                session.save(nowy);
                transaction.commit();
                tableNaprawa.getItems().clear();
                List<Naprawa> list1 = session.createCriteria(Naprawa.class).list();
                ObservableList<Naprawa> getNaprawa1 = FXCollections.observableArrayList(list1);

                tableNaprawa.getItems().addAll(list1);

                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);

                nKoszt.clear();
                nUsterka.clear();

            }

        });

        //usun naprawe
        Usun_n.setOnAction((ActionEvent event) -> {
            if (tableNaprawa.getSelectionModel().getSelectedItem() != null) {
                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();

                Naprawa naprawa = new Naprawa();
                naprawa = tableNaprawa.getSelectionModel().getSelectedItem();
                int id = naprawa.getId_naprawy();

                String usun = "delete from  Naprawa where  id_naprawy=:id";

                Query query = session.createQuery(usun);

                query.setInteger("id", id);
                query.executeUpdate();

                transaction.commit();
                List<Naprawa> list1 = session.createCriteria(Naprawa.class).list();
                ObservableList<Naprawa> getNaprawa1 = FXCollections.observableArrayList(list1);
                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
                tableNaprawa.getItems().remove(naprawa);

            }

        });

        //Edycja naprawy
        pobierzn.setOnAction((ActionEvent event) -> {
            if (tableNaprawa.getSelectionModel().getSelectedItem() != null) {

                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();
                Naprawa naprawa = new Naprawa();
                naprawa = tableNaprawa.getSelectionModel().getSelectedItem();

                nKoszt.setText(Float.toString(naprawa.getKoszt()));
                nUsterka.setText(naprawa.getUsterka());
                choiceKlient.setValue(naprawa.getKlient());
                choiceSamochod.setValue(naprawa.getSamochod());

                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
            }
        });

        okn.setOnAction((ActionEvent event) -> {
            if (nKoszt.getText() != null && !nKoszt.getText().isEmpty()
                    && nUsterka.getText() != null && !nUsterka.getText().isEmpty()) {
                Configuration configuration
                        = new Configuration().configure("hibernate.cfg.xml");

                configuration.addAnnotatedClass(Klient.class);
                configuration.addAnnotatedClass(Naprawa.class);
                configuration.addAnnotatedClass(Samochod.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                Session session = factory.openSession();
                Transaction transaction = session.beginTransaction();
                Naprawa naprawa2 = new Naprawa();
                naprawa2 = tableNaprawa.getSelectionModel().getSelectedItem();
                int id = naprawa2.getId_naprawy();
                Float koszt = (Float.parseFloat(nKoszt.getText()));
                String usterka = (nUsterka.getText());
                choiceKlient.getSelectionModel();
                Klient klient = new Klient();
                klient = choiceKlient.getSelectionModel().getSelectedItem();
                int id_klienta = klient.getId_klienta();

                choiceSamochod.getSelectionModel();
                Samochod samochod = new Samochod();
                samochod = choiceSamochod.getSelectionModel().getSelectedItem();
                int id_samochodu = samochod.getId_samochodu();

                String edytuj = "update Naprawa set koszt=:koszt, usterka=:usterka, id_klienta=:id_klienta, id_samochodu=:id_samochodu where id=:id";

                Query query = session.createQuery(edytuj);
                query.setInteger("id", id);
                query.setFloat("koszt", koszt);
                query.setString("usterka", usterka);
                query.setInteger("id_klienta", id_klienta);
                query.setInteger("id_samochodu", id_samochodu);
                query.executeUpdate();

                transaction.commit();
                tableNaprawa.getItems().clear();
                List<Naprawa> list1 = session.createCriteria(Naprawa.class).list();
                ObservableList<Naprawa> getNaprawa1 = FXCollections.observableArrayList(list1);

                tableNaprawa.getItems().addAll(list1);

                session.close();
                factory.close();
                StandardServiceRegistryBuilder.destroy(serviceRegistry);

                nKoszt.clear();
                nUsterka.clear();

            }

        });

        filtrk.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredKlient.setPredicate(klient -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (klient.getImie().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (klient.getNazwisko().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (klient.getNr_tel().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (klient.getAdres().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Klient> sortedKlient = new SortedList<>(filteredKlient);

            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedKlient.comparatorProperty().bind(tableKlient.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tableKlient.setItems(sortedKlient);
        });

        filtrs.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredSamochod.setPredicate(Samochod -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Samochod.getMarka().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (Samochod.getModel().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (Samochod.getNr_rejestracyjny().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (Samochod.getVIN().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Samochod> sortedSamochod = new SortedList<>(filteredSamochod);

            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedSamochod.comparatorProperty().bind(tableSamochod.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tableSamochod.setItems(sortedSamochod);
        });

        filtrn.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredNaprawa.setPredicate(Naprawa -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
    

                if (Naprawa.getUsterka().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } 
                return false; // Does not match.
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Naprawa> sortedNaprawa = new SortedList<>(filteredNaprawa);

            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedNaprawa.comparatorProperty().bind(tableNaprawa.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            tableNaprawa.setItems(sortedNaprawa);
        });

    }

}
