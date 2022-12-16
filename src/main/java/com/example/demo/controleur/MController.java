package com.example.demo.controleur;

import com.example.demo.modele.DAO.ModuleDAO;
import com.example.demo.Module;
import com.example.demo.Seance;
import com.example.demo.modele.DAO.SeanceDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.List;

public class MController {
    @FXML
    private FlowPane lundi9h;

    @FXML
    private Label code;
    @FXML
    private Label code1;

    @FXML
    private TextField codem;

    @FXML
    private TextField typesc;
    @FXML
    private TextField datesc;


    public ObservableList<Seance> data = FXCollections.observableArrayList();


    /*@FXML
    protected void displayModule() {
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            List<Seance> = seanceDAO.displayM_1();
            Seance s =
            String heured,heuref;
            heured = s.getHeureD();
            heuref = s.getHeureF();
            if(s.getHeureD() == "8"){
                code.setText(s.getCodeMod());
            } else {
                code1.setText(s.getCodeMod());
            }
            lundi9h.setBackground(Background.fill(Color.LIGHTPINK));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    @FXML
    protected void displayModule() {
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            List<Seance> seances = seanceDAO.displayM_1();
            String heured,heuref;
            for(Seance s : seances){
                heured = s.getHeureD();
                heuref = s.getHeureF();
                if(s.getHeureD() == "8"){
                    code.setText(s.getCodeMod());
                } else {
                    code1.setText(s.getCodeMod());
                }
                lundi9h.setBackground(Background.fill(Color.LIGHTPINK));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*@FXML
    protected void displayinfos() {
        try(SeanceDAO seanceDAO = SeanceDAO.create()) {
            Seance s = seanceDAO.displayM_1();
            codem.setText(s.getCodeMod());
            datesc.setText(s.getDate().toString());
            typesc.setText(s.getTypeSeance().toString());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/
}