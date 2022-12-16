package com.example.demo.modele.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.example.demo.Module;
import com.example.demo.Seance;

public class ModuleDAO extends JdbcDAO implements AutoCloseable{

    private static Logger log = Logger.getLogger(ModuleDAO.class.getName());
    private ModuleDAO() throws SQLException {
        findAll = connection.prepareStatement("SELECT * FROM MODULE");
        findbyId = connection.prepareStatement("SELECT * FROM MODULE WHERE CODEM=?");
    }

    public static ModuleDAO create() throws SQLException {
        return new ModuleDAO();
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

    /*public Seance displayM_1() throws SQLException {
        //List<Seance> seances = new ArrayList<>();
        findAll = connection.prepareStatement("SELECT CODEM,DATE,TYPESEANCE,HEURED,HEUREF FROM SEANCE");
        ResultSet rs = findAll.executeQuery();
        // Extract data from result set
        Seance s = null;
        while (rs.next()) {
            s = Seance.of(rs.getString("CODEM"), Seance.Type.valueOf(rs.getString("TYPESEANCE")),rs.getDate("DATE"), rs.getString("HEURED"), rs.getString("HEUREF"));
        }
        System.out.println(s);
        return s;
    }*/

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

    public Module findById(String module) throws SQLException {
        Module mod= null;
        findbyId.setString(1, module);
        ResultSet rs = findbyId.executeQuery();
        while (rs.next()) {
            mod = Module.of(rs.getString("CODEM"), rs.getString("LIBELLEMOD"), rs.getInt("VOLUMEHORAIRE"));
        }
        return mod;
    }

    public boolean exist(Module module) throws SQLException {
        return exist(module.getCodeMod());
    }

    public boolean exist(String module) throws SQLException {
        findbyId.setString(1, module);
        ResultSet rs = findbyId.executeQuery();
        return rs.next();
    }


    @Override
    public void close() throws SQLException {
        connection.close();
        log.info("DB connection closed");
    }
}