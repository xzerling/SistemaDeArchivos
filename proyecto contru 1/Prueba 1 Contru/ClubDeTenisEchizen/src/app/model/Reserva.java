/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

/**
 *
 * @author Yorch
 */
public class Reserva
{
    private String cancha;
    private String fecha;
    private long hora;
    private int tarifa;
    private Cliente cliente;
    private String estado;
    private boolean asistencia;

    public Reserva(String cancha, String fecha, long hora, int tarifa, Cliente cliente, String estado)
    {
        this.cancha = cancha;
        this.fecha = fecha;
        this.hora = hora;
        this.tarifa = tarifa;
        this.cliente = cliente;
        this.estado = estado;
        this.asistencia = false;
    }

    public String getCancha()
    {
        return cancha;
    }

    public void setCancha(String cancha)
    {
        this.cancha = cancha;
    }

    public String getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public long getHora()
    {
        return hora;
    }

    public void setHora(long hora)
    {
        this.hora = hora;
    }

    public int getTarifa()
    {
        return tarifa;
    }

    public void setTarifa(int tarifa)
    {
        this.tarifa = tarifa;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    public boolean isAsistencia()
    {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia)
    {
        this.asistencia = asistencia;
    }
    
    
    
    
}
