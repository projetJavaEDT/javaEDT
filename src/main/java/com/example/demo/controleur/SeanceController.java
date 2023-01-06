package com.example.demo.controleur;

import com.example.demo.exception.ExceptionEmail;
import com.example.demo.modele.DAO.*;
import com.example.demo.modele.ressources.Module;
import com.example.demo.modele.ressources.Salle;
import com.example.demo.modele.ressources.Seance;
import com.example.demo.modele.user.Enseignant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SeanceController {
    @FXML
    private ChoiceBox module;
    @FXML
    private ChoiceBox salle;
    @FXML
    private ChoiceBox types;
    @FXML
    private ChoiceBox ens;
    @FXML
    private DatePicker dateseance;
    @FXML
    private TextField debuts;
    @FXML
    private TextField fins;

    public void cancelSeance(MouseEvent mouseEvent) {
        module.setValue(null);
        salle.setValue(null);
        ens.setValue(null);
        types.setValue(null);
        dateseance.setValue(null);
        debuts.setText("");
        fins.setText("");
    }

    public void saveSeance(MouseEvent mouseEvent) {
        String val1 = module.getValue().toString();
        String val2 = salle.getValue().toString();
        String val3 = ens.getValue().toString();
        String val4 = types.getValue().toString();
        String val5 = dateseance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int val6 = Integer.parseInt(debuts.getText());
        int val7 = Integer.parseInt(fins.getText());
        try(SeanceDAO s = SeanceDAO.create()) {
            s.persist(val1,val2,val3, Seance.Type.valueOf(val4),java.sql.Date.valueOf(val5),val6,val7);
            infoBox("Séance sauvegardée!", "Succes");
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            infoBox("Séance non sauvegardée!", "Echec");
        }
    }

    public void sallesAvailable(ActionEvent actionEvent) throws SQLException, ExceptionEmail {
        SalleDAO salleDAO = SalleDAO.create();
        if(dateseance.getValue() != null){
            List<Salle> salles = salleDAO.findbyDate(dateseance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            for(Salle s : salles){
                salle.getItems().add(s.getCodeSalle());
            }
        }

        ModuleDAO moduleDAO = ModuleDAO.create();
        List<Module> modules = moduleDAO.findAll();
        for(Module m : modules){
            module.getItems().add(m.getCodeMod());
        }

        EnseignantDAO ensDAO = EnseignantDAO.create();
        List<Enseignant> enseignants = ensDAO.findAll();
        for(Enseignant e : enseignants){
            ens.getItems().add(e.getEmail());
        }

        types.getItems().add("CM");
        types.getItems().add("TD");
        types.getItems().add("TP");
    }

    public static void infoBox(String infoMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.show();
    }
}
