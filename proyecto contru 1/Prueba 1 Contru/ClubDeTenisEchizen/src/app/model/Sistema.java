/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yorch
 */
public class Sistema
{
    public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    public static ObservableList<String> nombreClientes = FXCollections.observableArrayList();;
    public static ArrayList<Cancha> canchas = new ArrayList<Cancha>();
    public static ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    public static ArrayList<Reserva> historialReservas = new ArrayList<Reserva>();
    public static ArrayList<Reserva> reservasMultadas = new ArrayList<Reserva>();
    private Archivo archivo = new Archivo();
    public static final String[] strDia = new String[]{"Lunes","Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

    
    /*Datos qe se requieren para una reserva*/
    public static long bloque;
    public static Dia dia;
    public static String cancha;
    public static int tarifa;
    public static Cliente cliente;

    public static void sistema()
    {
        ingresarCliente();
        ingresarCanchas();  
        agregarDiasCanchasInicial();
    }
    
    private static void agregarDiasCanchasInicial()
    {
        String diaFinal = LocalDate.now().plusDays(31).toString();
        for (int i = 0; i < CanchasSize(); i++)
        {            
            for (int j = 0; !LocalDate.now().plusDays(j).toString().equals(diaFinal); j++)
            {
                String fecha = CommandNames.formatoFecha.format(LocalDate.now().plusDays(j));
                String diaDeLaSemana = Sistema.strDia[LocalDate.now().plusDays(j).getDayOfWeek().getValue()-1];
                Dia dia = new Dia(fecha, diaDeLaSemana);
                getCanchas(i).addDia(dia);
            }            
        } 
    }
    
    private static void ingresarCliente()
    {
        clientes.add(new Cliente("Gregory"));
        Sistema.nombreClientes.add("Gregory");
        clientes.add(new Cliente("Patricio"));
        Sistema.nombreClientes.add("Patricio");
        clientes.add(new Cliente("Sergio"));
        Sistema.nombreClientes.add("Sergio");
        clientes.add(new Cliente("Daniela"));
        Sistema.nombreClientes.add("Daniela");
        verCliente();
    }
    
    private static void ingresarCanchas()
    {
        for (int i = 0; i < CommandNames.numeroCanchas; i++)
        {
            canchas.add(new Cancha(i+""));
        }        
    }
    
    private static void verCliente()
    {
        for (int i = 0; i < Sistema.ClienteSize(); i++)
        {
            System.out.println("nombre cliente " + i + ": " + Sistema.getClientes(i).getNombre());
        }
    }

    public static Cliente getClientes(int index)
    {
        return clientes.get(index);
    }

    public static int ClienteSize()
    {
        return clientes.size();
    }
    
    public static Cancha getCanchas(int index)
    {
        return canchas.get(index);
    }

    public static int CanchasSize()
    {
        return canchas.size();
    }
    
    public static void resetParamReserva()
    {
        Sistema.bloque = -1;
        Sistema.cancha = null;
        Sistema.dia = null; 
        Sistema.cliente=null;
    }
    
    public static void calTarifa()
    {
        if(Sistema.dia.getDia().equals("Sabado") || Sistema.dia.getDia().equals("Domingo") || Sistema.bloque>20)
        {
            Sistema.tarifa = CommandNames.tarifaEspecial;
        }
        else
        {
            Sistema.tarifa = CommandNames.tarifaNormal;
        }
    }
    
    public static Cliente buscaCiente(String nombreCliente)
    {
        for(int i=0; i < Sistema.ClienteSize(); i++)
        {
            if(Sistema.clientes.get(i).getNombre().equals(nombreCliente))
                
            {
                return Sistema.getClientes(i);
            }
        }
        return null;
    }
    
    public static void agregarReserva()
    {        
        Sistema.reservas.add(new Reserva(Sistema.cancha, Sistema.dia.getFecha(), Sistema.bloque, Sistema.tarifa, Sistema.cliente, "Reservado"));
        Sistema.canchas.get(Integer.valueOf(Sistema.cancha)).getDia(Sistema.dia.getFecha()).getBloque(Sistema.bloque).setEstado(false);
    }
    
    public static ObservableList<Reserva> buscarReservaCliente(String nombreCliente,ObservableList<Reserva> reservasObservable )
    {
        for (int i = 0; i < Sistema.reservas.size(); i++)
        {
            if(Sistema.reservas.get(i).getCliente().getNombre().equals(nombreCliente))
            {
                reservasObservable.add(Sistema.reservas.get(i));
            }
        }
        return reservasObservable;
    }
    
    public static ObservableList<Reserva> buscarReservaCanceladasCliente(String nombreCliente,ObservableList<Reserva> reservasObservable )
    {
        for (int i = 0; i < Sistema.reservasMultadas.size(); i++)
        {
            if(Sistema.reservasMultadas.get(i).getCliente().getNombre().equals(nombreCliente))
            {
                reservasObservable.add(Sistema.reservasMultadas.get(i));
            }
        }
        return reservasObservable;
    }
    
    

}
