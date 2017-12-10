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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YorchXD
 */
public class FXMLCrearArchivoController implements Initializable 
{    
    @FXML private Button botonCancelar;
    @FXML private Button botonAceptar;
    @FXML private TextField nombreArchivo;
    @FXML private TextField tamanioArchivo;

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
    public void cerrarVentana(ActionEvent event) 
    {
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.hide();
    }
    
    /**
     * Sirve para crear un archivo asi que faltan datos
     * @param event 
     */
    public void aceptarCrearArchivo(ActionEvent event)
    {
        Stage stage = (Stage) botonAceptar.getScene().getWindow();
        stage.hide();
    }
}
