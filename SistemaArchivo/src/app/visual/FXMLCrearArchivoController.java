/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.visual;

import app.model.Sistema;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yorch
 */
public class FXMLCrearArchivoController implements Initializable
{
    @FXML private ComboBox<String> listaSociosReserva;
    @FXML private Text cancha;
    @FXML private Text fecha;
    @FXML private Text hora;
    @FXML private Text tarifa;
    @FXML private Button botonCancelar, botonAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    } 
    
    /**
     * 
     * @param event 
     */
    public void aceptarReserva(ActionEvent event)
    {
        Sistema.cliente = Sistema.buscaCiente(listaSociosReserva.getValue());
        if(Sistema.cliente!=null)
        {
            Sistema.agregarReserva();
            Stage stage = (Stage) botonAceptar.getScene().getWindow();
            stage.hide();
            
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Alerta");
            a.setResizable(true);
            a.setContentText("Seleccione al cliente primero para poder realizar una reserva");
            a.showAndWait();
        }
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
    
}
