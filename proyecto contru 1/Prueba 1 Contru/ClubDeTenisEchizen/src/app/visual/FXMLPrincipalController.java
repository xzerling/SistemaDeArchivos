/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.visual;


import app.model.*;
import app.model.Sistema;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Yorch
 */
public class FXMLPrincipalController implements Initializable
{
    @FXML private BorderPane consultarDisponibilidadPane;
    @FXML private BorderPane cancelarReservaPane;
    @FXML private BorderPane validarAsistenciaPane;
    @FXML private BorderPane revisarMultasPane;
    @FXML private BorderPane menuPrincipalPane;
    @FXML private Text textoBarraNavegacion;
    @FXML private ComboBox<String> socioCancelarReserva, socioRevisarMultas, socioValidarAsistencia, numCancha;
    @FXML private ImageView iconoAutentificadorOpcion;    
    
    /*Variables para tabla de consulta de Diponibilidad*/
    @FXML private DatePicker fechaConsulta;
    @FXML private TableView<Bloque> tablaDisponibilidad;
    @FXML private TableColumn<Bloque, Long> colHoraDisponibilidad;
    @FXML private TableColumn<Bloque, String> colEstadoDisponibilidad;
    ObservableList<Bloque> bloques;
    
    /*variables para tabla de asistencia*/    
    private ObservableList<Reserva> reservas;
    @FXML private TableView<Reserva> tablaAsistencia;
    @FXML private TableColumn<Reserva, Long> colHoraAsistencia;
    @FXML private TableColumn<Reserva, String> colFechaAsistencia;
    
    /*variable para tabla de cancelar asistencia*/
    @FXML private TableView<Reserva> tablaCancelar;
    @FXML private TableColumn<Reserva, Long> colHoraCancelar;
    @FXML private TableColumn<Reserva, String> colFechaCancelar;
    
    /*variable para tabla de pagar multas*/
    @FXML private TableView<Reserva> tablaMultas;
    @FXML private TableColumn<Reserva, Long> colHoraMultas;
    @FXML private TableColumn<Reserva, String> colFechaMultas;
    @FXML private TableColumn<Reserva, String> colMotivoMultas;
    private ObservableList<Reserva> reservasCanceladas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {        
        this.iconoAutentificadorOpcion.setImage(new Image(CommandNames.imagenMenuPrincipal));
        
        //Tabla consultar disponibilidad de cancha
        this.colHoraDisponibilidad.setCellValueFactory( (TableColumn.CellDataFeatures<Bloque,Long> param) -> (ObservableValue) new SimpleLongProperty(param.getValue().getBloque()));
        this.colEstadoDisponibilidad.setCellValueFactory( (TableColumn.CellDataFeatures<Bloque,String> param) -> (ObservableValue) new SimpleStringProperty(param.getValue().getEstado()));
        
        this.bloques = FXCollections.observableArrayList();
        this.fechaConsulta.setValue(LocalDate.now());
        this.fechaConsulta.setDayCellFactory(deshabilitarDiasAntes());//para deshabilitar dias antes de la fecha actual
        
        //tabla validar asistencia
        this.colHoraAsistencia.setCellValueFactory( (TableColumn.CellDataFeatures<Reserva,Long> param) -> (ObservableValue) new SimpleLongProperty(param.getValue().getHora()));
        this.colFechaAsistencia.setCellValueFactory( (TableColumn.CellDataFeatures<Reserva,String> param) -> (ObservableValue) new SimpleStringProperty(param.getValue().getFecha()));
        this.reservas = FXCollections.observableArrayList();
        
        //tabla cancelar Asistencia
        this.colHoraCancelar.setCellValueFactory( (TableColumn.CellDataFeatures<Reserva,Long> param) -> (ObservableValue) new SimpleLongProperty(param.getValue().getHora()));
        this.colFechaCancelar.setCellValueFactory( (TableColumn.CellDataFeatures<Reserva,String> param) -> (ObservableValue) new SimpleStringProperty(param.getValue().getFecha()));
        
        //tabla pagar cancelacion
        this.colHoraMultas.setCellValueFactory( (TableColumn.CellDataFeatures<Reserva,Long> param) -> (ObservableValue) new SimpleLongProperty(param.getValue().getHora()));
        this.colFechaMultas.setCellValueFactory( (TableColumn.CellDataFeatures<Reserva,String> param) -> (ObservableValue) new SimpleStringProperty(param.getValue().getFecha()));
        this.colMotivoMultas.setCellValueFactory( (TableColumn.CellDataFeatures<Reserva,String> param) -> (ObservableValue) new SimpleStringProperty(param.getValue().getEstado()));
        this.reservasCanceladas = FXCollections.observableArrayList();
        Sistema.sistema();
                        
        ingresarListadoSocios();
        ingresarListadoCanchas();
         
    } 
    
    /**
     * listado de los socios para mostrar los datos graficamente
     */
    public void ingresarListadoSocios()
    {        
        this.socioCancelarReserva.setItems(Sistema.nombreClientes);
        this.socioValidarAsistencia.setItems(Sistema.nombreClientes);
        this.socioRevisarMultas.setItems(Sistema.nombreClientes);
    }
    
    /**
     * listado de las canchas a mostrar de manera grafica
     */
    public void ingresarListadoCanchas()
    {
        for(int i=0; i<Sistema.CanchasSize(); i++)
        {
            this.numCancha.getItems().add(Sistema.getCanchas(i).getId());
        }        
    }
    
    
    @FXML
    private void reservarCancha(ActionEvent event) throws IOException
    {
        if(reservar())
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLReservarCancha.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Reservar Cancha");
            stage.setScene(new Scene(root1));  
            stage.setResizable(false);
            stage.showAndWait();
            actualizarTablaConsulta();
            Sistema.resetParamReserva();
        }
                
    }
    
    /**
     * Este metodo cambia el stackpane a el panel de consultar disponibilidad de canchas
     * @param event 
     */
    public void cambiarDisponibilidadCanchaPane(ActionEvent event)
    {        
        this.consultarDisponibilidadPane.toFront();
        this.vaciarTablaConsulta();
        this.iconoAutentificadorOpcion.setImage(new Image(CommandNames.imagenCosultarDisponibilidad));
        this.consultarDisponibilidadPane.setVisible(true);
        this.menuPrincipalPane.setVisible(false);
        this.textoBarraNavegacion.setText("Consultar disponibilidad de canchas");
    }
    
    /**
     * Este metodo cambia el stackpane a el panel de cancelar reserva de canchas
     * @param event 
     */
    public void cambiarCancelarCanchaPane(ActionEvent event)
    {
        this.cancelarReservaPane.toFront();
        this.iconoAutentificadorOpcion.setImage(new Image(CommandNames.imagenCancelarReserva));
        this.cancelarReservaPane.setVisible(true);
        this.menuPrincipalPane.setVisible(false);
        this.textoBarraNavegacion.setText("Cancelar reserva de canchas");
    }
        
    /**
     * Este metodo cambia el stackpane a el panel de revisar multas
     * @param event 
     */
    public void cambiarRevisarMultaPane(ActionEvent event)
    {
        this.revisarMultasPane.toFront();
        this.iconoAutentificadorOpcion.setImage(new Image(CommandNames.imagenRevisarMulta));
        this.revisarMultasPane.setVisible(true);
        this.menuPrincipalPane.setVisible(false);
        this.textoBarraNavegacion.setText("Revisar multas");
    }
    
    /**
     * Este metodo cambia el stackpane a el panel de validar asistencia
     * @param event 
     */
    public void cambiarValidarAsistenciaPane(ActionEvent event)
    {
        this.validarAsistenciaPane.toFront();
        this.iconoAutentificadorOpcion.setImage(new Image(CommandNames.imagenValidarAsistencia));
        this.validarAsistenciaPane.setVisible(true);
        this.menuPrincipalPane.setVisible(false);
        this.textoBarraNavegacion.setText("Validar asistencia de reservas");
    }
        
    /**
     * Este metodo cambia el stackpane a el panel de menú principal
     * @param event 
     */
    public void cambiarMenuPrincipaPane(ActionEvent event)
    {
        this.menuPrincipalPane.toFront();
        this.iconoAutentificadorOpcion.setImage(new Image(CommandNames.imagenMenuPrincipal));
        this.menuPrincipalPane.setVisible(true);
        this.consultarDisponibilidadPane.setVisible(false); 
        this.cancelarReservaPane.setVisible(false);
        this.revisarMultasPane.setVisible(false);
        this.validarAsistenciaPane.setVisible(false);
        this.textoBarraNavegacion.setText("Menú principal");
    }
    
    /**
     * Este metodo cierra la aplicacion completamente
     * @param event 
     */
    public void cerrarAplicacion(ActionEvent event) throws IOException 
    {
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
    
    public void datosTablaConsulta()
    {  
        this.vaciarTablaConsulta();
        
        LocalDate fecha = this.fechaConsulta.getValue();
        Sistema.cancha = this.numCancha.getValue();
        if(fecha!=null && Sistema.cancha!=null)
        {            
            Cancha cancha = Sistema.getCanchas(Integer.parseInt(Sistema.cancha));
            Sistema.dia = cancha.getDia(CommandNames.formatoFecha.format(fecha));
            if(Sistema.dia!=null)
            {
                for (int i = 0; i < Sistema.dia.getBloques().size(); i++)
                {
                    this.bloques.add(Sistema.dia.getBloques().get(i));
                }
                
                this.llenarTablaConsulta();                 
            }    
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Alerta");
            a.setResizable(true);
            a.setContentText("Seleccione una fecha y cancha primero para poder buscar los bloques disponibles");
            a.showAndWait();
        }             
    }
    
    public void actualizarTablaConsulta()
    {
        vaciarTablaConsulta();
        for (int i = 0; i < Sistema.dia.getBloques().size(); i++)
        {
            this.bloques.add(Sistema.dia.getBloques().get(i));
        }

        this.llenarTablaConsulta();
    }   

    public void actualizarTablaAsistencia()
    {
        vaciarTablaAsistencia();
        llenarTablaAsistencia();
    } 
    
    public void actualizarTablaCancelar()
    {
        vaciarTablaCancelar();
        llenarTablaCancelar();
    }
    
    public void actualizarTablaMultas()
    {
        vaciarTablaCancelar();
        llenarTablaCancelar();
    }
    
    public void llenarTablaConsulta()
    {
        this.tablaDisponibilidad.setItems(this.bloques);
    }
    
    public void vaciarTablaConsulta()
    {
        this.bloques.clear();
    }
    
    public void llenarTablaAsistencia()
    {
        this.tablaAsistencia.setItems(this.reservas);
    }
    
    public void vaciarTablaAsistencia()
    {
        this.reservas.clear();
    }
    
    public void llenarTablaCancelar()
    {
        this.tablaCancelar.setItems(this.reservas);
    }
    
    public void vaciarTablaCancelar()
    {
        this.reservas.clear();
    }
    
    public void llenarTablaMultas()
    {
        this.tablaMultas.setItems(this.reservasCanceladas);
    }
    
    public void vaciarTablaMultas()
    {
        this.reservasCanceladas.clear();
    }
    
    /**
     * Ayuda a controlar los dias no habilitados para realizar reserva
     * @return 
     */
    public Callback deshabilitarDiasAntes()
    {        
        final Callback<DatePicker, DateCell> dayCellFactory;
        dayCellFactory = new Callback<DatePicker, DateCell>() 
        {
            @Override
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell() 
                {
                    @Override 
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) 
                        {
                            actualizarFechaActual();//método creado para agregar nuevo dia (sin terminar)
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                        if (item.isAfter(LocalDate.now().plusDays(30)))
                        {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }      
    
    public boolean reservar()
    {
        Bloque seleccionado = this.tablaDisponibilidad.getSelectionModel().getSelectedItem();
        if(seleccionado!=null)
        {
            if(seleccionado.getEstado().equals("Disponible"))
            {
                Sistema.bloque = (int) seleccionado.getBloque();
                System.out.println("hora: " + seleccionado.getBloque());
                return true;
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Club de tenis Echizen");
                a.setHeaderText("Alerta");
                a.setResizable(true);
                a.setContentText("seleccione un bloque disponible");
                a.showAndWait();
                
            }
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Alerta");
            a.setResizable(true);
            a.setContentText("Seleccione un bloque primero para poder reservar");
            a.showAndWait();
            
        }
        return false;
    }
    
    public void datosTablaAsistencia()
    {
        vaciarTablaAsistencia();
        String nombreCliente = this.socioValidarAsistencia.getValue();
        if(nombreCliente!=null)
        {
            this.reservas = Sistema.buscarReservaCliente(nombreCliente, reservas);
            if(reservas.size()!=0)
            {
                llenarTablaAsistencia();
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Club de tenis Echizen");
                a.setHeaderText("Alerta");
                a.setResizable(true);
                a.setContentText("No tiene reservas asociadas");
                a.showAndWait();
            }
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Alerta");
            a.setResizable(true);
            a.setContentText("Seleccione un cliente primero");
            a.showAndWait();
        }         
    }
    
    public void validarAsistencia()
    {        
        Reserva seleccionado = this.tablaAsistencia.getSelectionModel().getSelectedItem();
        if(seleccionado!=null)
        {
            seleccionado.setAsistencia(true);
            seleccionado.setEstado("Usada");
            this.reservas.remove(seleccionado);
            Sistema.historialReservas.add(seleccionado);
            Sistema.reservas.remove(seleccionado);           
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Alerta");
            a.setResizable(true);
            a.setContentText("Debe seleccionar una reserva primero");
            a.showAndWait();
        }
    }
    
    public void datosTablaCancelar()
    {
        vaciarTablaCancelar();
        String nombreCliente = this.socioCancelarReserva.getValue();
        if(nombreCliente!=null)
        {
            this.reservas = Sistema.buscarReservaCliente(nombreCliente, reservas);
            if(!reservas.isEmpty())
            {
                llenarTablaCancelar();
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Club de tenis Echizen");
                a.setHeaderText("Alerta");
                a.setResizable(true);
                a.setContentText("No tiene reservas canceladas asociadas");
                a.showAndWait();
            }
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Alerta");
            a.setResizable(true);
            a.setContentText("Seleccione un cliente primero");
            a.showAndWait();
        }       
    }
    
    public void cancelarReserva()
    {        
        Reserva seleccionado = this.tablaCancelar.getSelectionModel().getSelectedItem();
        if(seleccionado!=null)
        {
            seleccionado.setEstado("No usada");
            this.reservas.remove(seleccionado);
            Sistema.reservasMultadas.add(seleccionado);
            Sistema.reservas.remove(seleccionado);
            
            /*Es para habilitar un el bloque de un dia*/
            Cancha cancha;
            for (int i = 0; i < Sistema.CanchasSize(); i++)
            {
                if(Sistema.getCanchas(i).getId().equals(seleccionado.getCancha()))
                {
                    cancha =Sistema.getCanchas(i);
                    Dia dia = cancha.getDia(seleccionado.getFecha());
                    dia.getBloque(seleccionado.getHora()).setEstado(true);
                    break;
                }
            }
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Alerta");
            a.setResizable(true);
            a.setContentText("Debe seleccionar una reserva primero");
            a.showAndWait();
        }
    }
    
    
    public void datosTablaMultas()
    {
        vaciarTablaMultas();
        String nombreCliente = this.socioRevisarMultas.getValue();
        if(nombreCliente!=null)
        {
            this.reservasCanceladas = Sistema.buscarReservaCanceladasCliente(nombreCliente, reservasCanceladas);
            if(!reservasCanceladas.isEmpty())
            {
                llenarTablaMultas();
            }
            else
            {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Club de tenis Echizen");
                a.setHeaderText("Alerta");
                a.setResizable(true);
                a.setContentText("No tiene reservas asociadas");
                a.showAndWait();
            }
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Alerta");
            a.setResizable(true);
            a.setContentText("Seleccione un cliente primero");
            a.showAndWait();
        }        
    }
    
    public void mensajePagar()
    {
        Reserva seleccionado = this.tablaMultas.getSelectionModel().getSelectedItem();
        if (seleccionado!=null)
        {
             Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Confirmacion");
            a.setResizable(true);
            a.setContentText("A pagado la multa por cancelar una reserva\n"
                    + "La multa es de " + CommandNames.tarifaMultaCancelacionReserva + "\n\n"
                            + "su vuelto es de " + (seleccionado.getTarifa()-CommandNames.tarifaMultaCancelacionReserva));
            a.showAndWait();
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Club de tenis Echizen");
            a.setHeaderText("Alerta");
            a.setResizable(true);
            a.setContentText("Debe seleccionar una multa primero para luego pagar");
            a.showAndWait();
        }       
    }
    
    /*Metodos sin terminar*/
    public void actualizarFechaActual()
    {
        aplicarMultaInasistencia();
    }
    
    public void aplicarMultaInasistencia()
    {
        
    }
    
}
