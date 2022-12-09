package com.example.demo;

import com.example.demo.Modele.DAO.ModuleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class MController {
    @FXML
    private TableView<Seance> tabedt;
    @FXML
    private TableColumn<Module, String> horaires;
    @FXML
    private TableColumn<Module, String> lundi;
    @FXML
    private TableColumn<Module, String> mardi;

    public ObservableList<Seance> data = FXCollections.observableArrayList();


    @FXML
    protected void displayModule() {
        try(ModuleDAO modeDAO = ModuleDAO.create()) {
            System.out.println(modeDAO.displayM_1());
            //doit recup toutes les seances
            data.add(modeDAO.displayM_1());
            horaires.setCellValueFactory(new PropertyValueFactory<Module, String>("horaires"));
            lundi.setCellValueFactory(new PropertyValueFactory<Module, String>("codeMod"));
            tabedt.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}