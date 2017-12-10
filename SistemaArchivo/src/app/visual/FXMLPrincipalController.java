/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.visual;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Yorch
 */
public class FXMLPrincipalController implements Initializable
{
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {        
        
    } 
    
    @FXML
    public void prueba(ActionEvent event) throws IOException
    {
        System.out.println("entro a la burrada");
    }

    @FXML
    public void crearArchivo(ActionEvent event) throws IOException
    {
        System.out.println("se metio a crear un archivo");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLCrearArchivo.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("Crear Archivo");
        stage.setScene(new Scene(root1));  
        stage.setResizable(false);
        stage.showAndWait();
        System.out.println("finallizo de crear un archivo");
                
    }
        
    /**
     * Este metodo cambia el stackpane a el panel de menú principal
     * @param event 
     */
    @FXML
    public void cambiarMenuPrincipaPane(ActionEvent event)
    {
        
    }
    
    /**
     * Este metodo cierra la aplicacion completamente
     * @param event 
     */
    @FXML
    public void cerrarAplicacion(ActionEvent event) throws IOException 
    {
        System.out.println("entro a la burrada");
        app.model.CommandNames.boxMessage = "¿Esta seguro que desea salir de la aplicacion?";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLMensajeOpciones.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Confirmacion");
        stage.setScene(new Scene(root1));  
        stage.setResizable(false);
        stage.showAndWait();
        if (app.model.CommandNames.acceptedBox)
        {
            app.model.CommandNames.acceptedBox = false;
            Platform.exit();
        }        
    }

    
}
