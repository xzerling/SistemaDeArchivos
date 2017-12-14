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
    public void aceptarCrearArchivo(ActionEvent event) throws IOException
    {           
        String nombre = this.nombreArchivo.getText();
        int tamanio = Integer.parseInt(this.tamanioArchivo.getText());
        boolean tamanioEstimado= Sistema.estimarEspacioSuficiente(tamanio);
        
        if(!Sistema.esUnico(nombre) || nombre.length()>9 || tamanio<=0 || !tamanioEstimado)
        {
                       
            if(tamanio<=0)
            {
                this.mensaje("El tamaño del disco debe ser un valor positivo");
            }  
            else if(!tamanioEstimado)
            {
                this.mensaje("No hay espacio suficiente");
            }
            else if(nombre.length()>9)
            {
                this.mensaje("El tamaño maximo de nombre son 8 caracteres");
            }
            else
            {
                this.mensaje("El nombre ya ha sido utilizado");
            }                
        }
        
        if(tamanio>0  && Sistema.esUnico(nombre))
        {
            Sistema.crear(tamanio, nombre);
            this.mensaje("Archivo creado");
            Stage stage = (Stage) botonAceptar.getScene().getWindow();
            stage.hide();
        }
        
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
