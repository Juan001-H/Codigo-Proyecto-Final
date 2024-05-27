package co.edu.uniquindio.poo;

/**
 * Hello world!
 *
 */
import java.time.LocalTime;

public class App {
    public static void main(String[] args) {
        
        Puesto puesto = new Puesto(2, 2);

        Parqueadero perseo = new Parqueadero("Perseo");

        Carro carro_1 = new Carro("KDN-103", "2013", "Tomas", LocalTime.of(6,0),3000);
        MotoClasica moto_1 = new MotoClasica("PTG-59E", "2021", "Juan",LocalTime.of(9,0),85,1000);
        MotoHibrida moto_2 = new MotoHibrida("HJK-36G", "2023", "Ryan Castro", LocalTime.of(9, 0),154,1000);

        perseo.agregarPuestos(puesto);
        System.out.println(perseo.obtenerRegistro());

        puesto.agregarVehiculo(carro_1);
        puesto.agregarVehiculo(moto_1);
        puesto.agregarVehiculo(moto_2);

    perseo.horaSalida(LocalTime.of(8, 30, 0),"KDN-103");
        System.out.println(perseo.calcularTarifa("KDN-103"));

    System.out.println( perseo.obtenerDueñoVehiculo(0,0));

    System.out.println(perseo.obtenerRegistro());

    perseo.retirarVehiculo("PTG-59E");

        System.out.println( perseo.obtenerReporteDia());
    }
}