package co.edu.uniquindio.poo;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MotoHibrida extends Moto{
    private int tarifa;



    public MotoHibrida(String placa, String modelo, String propietario, LocalTime horaEntrada, int velocidad,
            int tarifa) {
        super(placa, modelo, propietario, horaEntrada, velocidad);
        this.tarifa = tarifa;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public String toString() {
        return "MotoHibrida{" +
                "placa='" + getPlaca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", propietario='" + getPropietario() + '\'' +
                ", velocidad=" + getVelocidad() +
                ", tarifa=" + tarifa +
                '}';
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

