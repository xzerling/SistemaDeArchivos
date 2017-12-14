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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author YorchXD
 */
public class FXMLEscribirDesdeController implements Initializable 
{

    @FXML private Button botonCancelar;
    @FXML private Button botonAceptar;
    @FXML private TextField nombreArchivo;
    @FXML private TextField posicionArchivo;
    @FXML private TextArea agregarTexto;

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
    public void cerrarVentanaAceptar(ActionEvent event) throws IOException
    {
        
        String nombre = this.nombreArchivo.getText();
        int pos = Integer.parseInt(this.posicionArchivo.getText());
        int posArchivo = Sistema.buscarArchivo(nombre);
        boolean espacioMemoria = Sistema.verificarEspacio(pos, nombre, posArchivo);
        
        if(nombre.length()>9 || pos<=0 || posArchivo==-1 || !espacioMemoria)
        {                       
            if(pos<=0)
            {
                this.mensaje("La posición que se desea leer del archivo debe ser un valor positivo");
            }
            else if(nombre.length()>9)
            {
                this.mensaje("El tamaño maximo de nombre son 8 caracteres");
            } 
            else if(posArchivo==-1 )
            {
                this.mensaje("El archivo no existe");
            }
            else if(!espacioMemoria)
            {
                this.mensaje("No hay espacio suficiente");
            }
        }
        
        else
        {
            System.out.println("texto a ingresar:"+this.agregarTexto.getText());
            Sistema.writeAt(pos, this.agregarTexto.getText(), posArchivo);
            this.mensaje("Se han guardado los cambios");
            app.model.CommandNames.acceptedBox = true;
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