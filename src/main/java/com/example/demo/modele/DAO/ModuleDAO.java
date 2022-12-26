package com.example.demo.modele.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.example.demo.modele.ressources.Module;

public class ModuleDAO extends JdbcDAO implements AutoCloseable{

    private static Logger log = Logger.getLogger(ModuleDAO.class.getName());

    public static ModuleDAO create() throws SQLException {
        return new ModuleDAO();
    }


    private ModuleDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM MODULE");
        findby = connection.prepareStatement("SELECT * FROM MODULE WHERE CODEM=?");
        findbyMod = connection.prepareStatement("SELECT * FROM MODULE WHERE LIBELLEMOD=?");
    }


    public List<Module> findAll() throws SQLException {
        List<Module> modules = new ArrayList<>();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            modules.add(Module.of(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getInt("VOLUMEHORAIRE")));
        }
        return modules;
    }


    public Module findById(String module) throws SQLException {
        Module mod= null;
        findby.setString(1, module);
        ResultSet rs = findby.executeQuery();
        while (rs.next()) {
            mod = Module.of(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getInt("VOLUMEHORAIRE"));
        }
        return mod;
    }

    public Module findByMod(String module) throws SQLException {
        Module mod= null;
        findbyMod.setString(1, module);
        ResultSet rs = findbyMod.executeQuery();
        while (rs.next()) {
            mod = Module.of(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getInt("VOLUMEHORAIRE"));
        }
        return mod;
    }


    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}

    /*public void displayMOLD(TableView tabedt, TableColumn codeMod, TableColumn libelleMod, TableColumn volumeHoraire) throws SQLException {
        data = FXCollections.observableArrayList();
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        while (rs.next()) {
            /*ObservableList<String> row = FXCollections.observableArrayList();
            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(rs.getString(i));
            }
            System.out.println("Row [1] added "+row );
            data.add((ObservableList) Module.of(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getInt("VOLUMEHORAIRE")));
            System.out.println(data);
        }

        //FINALLY ADDED TO TableView
        codeMod.setCellValueFactory(new PropertyValueFactory<Module, String>("codeMod"));
        libelleMod.setCellValueFactory(new PropertyValueFactory<Module, String>("libelleMod"));
        volumeHoraire.setCellValueFactory(new PropertyValueFactory<Module, Integer>("volumeHoraire"));
        tabedt.setItems(data);
    }*/