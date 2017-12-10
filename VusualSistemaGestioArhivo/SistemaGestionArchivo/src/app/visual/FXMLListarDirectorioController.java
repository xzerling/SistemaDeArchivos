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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YorchXD
 */
public class FXMLListarDirectorioController implements Initializable 
{
    /*variables para tabla de asistencia*/    
    /*@FXML private TableView<Bloque> tablaDisponibilidad;
    @FXML private TableColumn<Bloque, Long> colHoraDisponibilidad;
    @FXML private TableColumn<Bloque, String> colEstadoDisponibilidad;
    ObservableList<Bloque> bloques;*/
    
    @FXML private Button botonVolver;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        /*this.colHoraDisponibilidad.setCellValueFactory( (TableColumn.CellDataFeatures<Bloque,Long> param) -> (ObservableValue) new SimpleLongProperty(param.getValue().getBloque()));
        this.colEstadoDisponibilidad.setCellValueFactory( (TableColumn.CellDataFeatures<Bloque,String> param) -> (ObservableValue) new SimpleStringProperty(param.getValue().getEstado()));
        
        this.bloques = FXCollections.observableArrayList();
        this.fechaConsulta.setValue(LocalDate.now());
        this.fechaConsulta.setDayCellFactory(deshabilitarDiasAntes());*/ //para deshabilitar dias antes de la fecha actual
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
