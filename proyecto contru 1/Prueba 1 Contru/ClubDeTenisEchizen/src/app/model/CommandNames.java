/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author Yorch
 */
public class CommandNames
{
    static public String boxMessage;     
    static public boolean acceptedBox = false;
    static int numeroCanchas = 6;
    static int iniciolHoraDia = 8;
    static int finalHoraDia = 23;
    static public String imagenMenuPrincipal = "app/resource/images/casa.png";
    static public String imagenCosultarDisponibilidad = "app/resource/images/consultar-disponibilidad.png";
    static public String imagenRevisarMulta = "app/resource/images/revisar-multas.png";
    static public String imagenCancelarReserva= "app/resource/images/cancelar-reserva.png";
    static public String imagenValidarAsistencia = "app/resource/images/validar-asistencia.png"; 
    static public DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    static public final int tarifaNormal = 10000;
    static public final int tarifaEspecial = 12000;
    static public final int tarifaMultaInasistencia = 3000;
    static public final int tarifaMultaCancelacionReserva = 2000;
}
