package co.edu.uniquindio.poo;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Carro extends Vehiculo {

    private int tarifa;

    public Carro(String placa, String modelo, String propietario, LocalTime horaEntrada, int tarifa) {
        super(placa, modelo, propietario, horaEntrada);
        this.tarifa = tarifa;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    // metodo to string
    public String toString() {
        return "Carro [tarifa=" + tarifa + ", getTarifa()=" + getTarifa() + ", getPlaca()=" + getPlaca()
                + ", getModelo()=" + getModelo() + ", getPropietario()=" + getPropietario() + ", getHoraEntrada()="
                + getHoraEntrada() + "]";
    }

    @Override
    // Metodo para calcular la tarifa de un carro y regresa un entero
    protected int obtenerTarifa() {
        int valor = 0;
        if (getHoraSalida() != null) {
            long restaTiempo = ChronoUnit.HOURS.between(getHoraEntrada(), getHoraSalida());
            int tiempoTotal = (int) restaTiempo;
            valor = tarifa * (tiempoTotal);
        } else if (getHoraSalida() == null) {
            System.out.println("Aun no ha salido el vehiculo con placas: " + getPlaca());
        }
        return valor;
    }

}

