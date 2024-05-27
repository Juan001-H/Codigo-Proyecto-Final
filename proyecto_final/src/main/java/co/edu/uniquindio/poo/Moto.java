package co.edu.uniquindio.poo;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class Moto extends Vehiculo {
    private int velocidad;
    private int tarifa;



    public Moto(String placa, String modelo, String propietario, LocalTime horaEntrada, int velocidad) {
        super(placa, modelo, propietario, horaEntrada);
        this.velocidad = velocidad;
        
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public String toString() {
        return "Moto [velocidad=" + velocidad + "]";
    }

    



    //Metodo para calcular la tarifa de una moto y retornarla
    protected int obtenerTarifa() {
    int valor = 0;
        long restaTiempo = ChronoUnit.HOURS.between(getHoraEntrada(),getHoraSalida());
        int tiempoTotal = (int) restaTiempo;
        valor = tarifa*(tiempoTotal);
        return valor;
    }

}
