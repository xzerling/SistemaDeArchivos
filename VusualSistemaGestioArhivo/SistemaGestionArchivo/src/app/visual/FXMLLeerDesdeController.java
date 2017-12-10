/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.visual;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YorchXD
 */
public class FXMLLeerDesdeController implements Initializable 
{

    @FXML private Button botonVolver;
    @FXML private Button botonMostrar;
    @FXML private TextField nombreArchivo;
    @FXML private TextField posicionArchivo;
    @FXML private TextArea documentoImpreso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    /**
     * Sirve para crear un archivo asi que faltan datos
     * @param event 
     */
    public void MostrarArchivo(ActionEvent event)
    {
        
    }  
    
}
