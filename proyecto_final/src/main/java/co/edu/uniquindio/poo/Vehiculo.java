package co.edu.uniquindio.poo;
import java.time.LocalTime;


public abstract class Vehiculo {
    
    private final String placa;
    private final String modelo;
    private final String propietario;
    protected final LocalTime horaEntrada;
    private  LocalTime horaSalida;
    
    public Vehiculo(String placa, String modelo, String propietario, LocalTime horaEntrada) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
        this.horaEntrada = horaEntrada;
        this.horaSalida = null;
    }
    public String getPlaca() {
        return placa;
    }
    public String getModelo() {
        return modelo;
    }
    public String getPropietario() {
        return propietario;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    @Override
    public String toString() {
        return "Vehiculo [placa=" + placa + ", modelo=" + modelo + ", propietario=" + propietario + ", horaEntrada="
                + horaEntrada + "]";
    }
    protected abstract int obtenerTarifa( );

    public LocalTime getHoraSalida() {
        return horaSalida;
    }
    public void horaSalida(LocalTime of) {
        setHoraSalida(of);
    }
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
}
}

