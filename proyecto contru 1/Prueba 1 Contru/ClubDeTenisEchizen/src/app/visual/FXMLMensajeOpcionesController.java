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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rjerez1992
 */
public class FXMLMensajeOpcionesController implements Initializable {

    @FXML Button botonAceptar;
    @FXML Text textoMensaje;
    
    /**
     * Cierra la ventana con un estado de aceptacion del mensaje
     * @param event 
     */
    public void cerrarVentanaAceptar(ActionEvent event){
        app.model.CommandNames.acceptedBox = true;
        Stage stage = (Stage) botonAceptar.getScene().getWindow();
        stage.hide();
    }
    
    /**
     * Cierra la ventana sin variar el parametro de aceptacion, por ende; queda en negativo.
     * @param event 
     */
    public void cerrarVentana(ActionEvent event){
        Stage stage = (Stage) botonAceptar.getScene().getWindow();
        stage.hide();
    }
    
    /**
     * Inicializa el controlador y deja el parametro de aceptacion en negativo
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app.model.CommandNames.acceptedBox = false;
        textoMensaje.setText(app.model.CommandNames.boxMessage);
    }        
}
