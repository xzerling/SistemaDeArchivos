/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.visual;

import app.model.Sistema;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author YorchXD
 */
public class FXMLEliminarArchivoController implements Initializable {

    @FXML Button botonAceptar;
    @FXML TextField nombreArchivo;
    
    /**
     * Cierra la ventana con un estado de aceptacion del mensaje
     * @param event 
     */
    public void cerrarVentanaAceptar(ActionEvent event) throws IOException
    {
        
        if(Sistema.remove(this.nombreArchivo.getText()))
        {
            this.mensaje("Se ha eliminado el archivo");
            app.model.CommandNames.acceptedBox = true;
            Stage stage = (Stage) botonAceptar.getScene().getWindow();
            stage.hide();
        }
        else
        {
            this.mensaje("El archivo no existe");
        }        
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
    }   
    
    private void mensaje(String mensaje) throws IOException
    {
        app.model.CommandNames.boxMessage = mensaje;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLMensaje.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Confirmacion");
        stage.setScene(new Scene(root1));  
        stage.setResizable(false);
        stage.showAndWait();
    }
}
