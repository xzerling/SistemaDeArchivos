/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.visual;

import app.model.Directorio;
import app.model.FCB;
import app.model.Sector;
import app.model.Sistema;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YorchXD
 */
public class FXMLListarDirectorioController implements Initializable 
{
    /*variables para tabla de asistencia*/    
    @FXML private TextArea tablaDirectorio;   
    @FXML private Button botonVolver;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        tablaDirectorio.setText(Sistema.list());
    }    
    
    /**
     * Cierra la ventana.
     * @param event 
     */
    @FXML
    public void VolverMenuPrincipal(ActionEvent event) 
    {
        Stage stage = (Stage) botonVolver.getScene().getWindow();
        stage.hide();
    }
    
    
}

