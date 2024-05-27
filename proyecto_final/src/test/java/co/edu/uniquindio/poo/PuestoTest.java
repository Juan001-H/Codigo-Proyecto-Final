/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PuestoTest {
    private Puesto puesto;

    @BeforeEach
    public void setUp() {

        puesto = new Puesto(2, 2);

    }

    @Test
    public void testAgregarVehiculoExitoso() {
        Carro carro = new Carro("ABC123", "Toyota", "Juan", null, 0);
        puesto.agregarVehiculo(carro);
        assertEquals(carro, puesto.getListaVehiculos()[0][0]);
    }

    @Test
    public void testObtenerDueñoVehiculoExistente() {
        Carro carro = new Carro("KDN-103", "Toyota", "Juan", LocalTime.now(), 3000);
        puesto.getListaVehiculos()[0][0] = carro;
        String propietario = puesto.obtenerDueño(0, 0);
        assertEquals("El dueño del vehículo en el puesto 0,0 es: Juan", propietario);

    }

    @Test
    public void testObtenerVehiculoExistente() {
        Carro carro = new Carro("KDN-103", "Toyota", "Juan", LocalTime.now(), 3000);
        puesto.getListaVehiculos()[0][0] = carro;
        String informacionVehiculo = puesto.obtenerVehiculo(0, 0);
        assertEquals("El vehículo en el puesto 0,0 es: KDN-103 y su modelo: Toyota", informacionVehiculo);
    }

    @Test
    public void testObtenerDisponibilidadPuestoOcupado() {
        Puesto puesto = new Puesto(1, 1);
        puesto.agregarVehiculo(new Carro("ABC123", "BMW", "Juan", LocalTime.now(), 3000));
        assertTrue(puesto.obtenerDisponibilidadPuesto(0, 0).contains("ocupado"));
    }

    @Test
    public void testReporteDia() {

        var carro = new Carro("ABC123", "Toyota", "Juan", LocalTime.now(), 3000);
        var moto = new MotoClasica("XYZ789", "Honda", "Pedro", LocalTime.now(), 150, 2500);
        puesto.agregarVehiculo(carro);
        puesto.agregarVehiculo(moto);

        puesto.reporteDia();

        assertTrue(puesto.getListaReporte().contains(carro.toString()));
        assertTrue(puesto.getListaReporte().contains(moto.toString()));
    }
    
}

