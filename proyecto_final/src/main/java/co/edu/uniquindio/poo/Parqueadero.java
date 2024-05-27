package co.edu.uniquindio.poo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parqueadero {
    private final String nombre;
    private ArrayList<Registro> historialVehiculos;
    public Puesto puestos;

    public Parqueadero(String nombre) {
        this.nombre = nombre;
        historialVehiculos = new ArrayList<>();

    }

    // Métodos get y set
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Registro> getHistorialVehiculos() {
        return historialVehiculos;
    }

    public Puesto getPuestos() {
        return puestos;
    }

    public void setPuestos(Puesto puestos) {
        this.puestos = puestos;
    }

    @Override
    public String toString() {
        return "Parqueadero [nombre=" + nombre + ", puestos=" + puestos + "]";
    }

    // Metodo para agregar puestos al parqueadero
    public void agregarPuestos(Puesto puestos) {
        setPuestos(puestos);
    }

    // Metodo en para calcular la tarifa segun la placa

    public String calcularTarifa(String placa) {
        String mensaje = "";
        int tarifa = puestos.obtenerTarifa(placa);
        mensaje = "Su tarifa a pagar es de: " + tarifa;
        return mensaje;
    }

    // Metodo para obtener el propietario de un vehiculo segun la fila y columna en
    // la que este ubicado
    public String obtenerDueñoVehiculo(int fila, int columna) {
        String dueño = "";
        dueño = puestos.obtenerDueño(fila, columna);
        return dueño;
    }

    // Metodo para obtener un vehiculo segun la fila y columna en la q este ubicado
    public String obtenerVehiculoPuesto(int fila, int columna) {
        String vehiculo = "";
        vehiculo = puestos.obtenerVehiculo(fila, columna);
        return vehiculo;
    }

    // metodo para comprobar si hay puestos disponibles
    public String comprobarPuesto(int fila, int columna) {
        String validacion = "";
        validacion = puestos.obtenerDisponibilidadPuesto(fila, columna);
        return validacion;
    }

    // Metodo para poder obtener el registro del parqueadero
    public String obtenerRegistro() {
        String registro = "";
        registro = puestos.registro();

        return registro;
    }

    // Metodo para retirar un vehiculo del parqueadero
    public void retirarVehiculo(String placa) {
        puestos.retirarVehiculo(placa);
    }
    // Metodo para obtener el reporte del dia del parqueadero

    public String obtenerReporteDia() {
        String reporte;
        reporte = puestos.getListaReporte();
        return reporte;
    }

    // metodo para registrar la hora de salida de un vehiculo segun la placa
    public void horaSalida(LocalTime horaSalida, String placa) {
        puestos.horaSalida(horaSalida, placa);
    }

    public String generarReporteDiario(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }

        double totalDiario = 0;
        Map<String, Double> totalesPorTipo = new HashMap<>();
        Map<String, Integer> conteoPorTipo = new HashMap<>();

        totalesPorTipo.put("Clasica", 0.0);
        totalesPorTipo.put("Hibrida", 0.0);
        totalesPorTipo.put("Carro", 0.0);

        conteoPorTipo.put("Clasica", 0);
        conteoPorTipo.put("Hibrida", 0);
        conteoPorTipo.put("Carro", 0);

        for (Registro registro : historialVehiculos) {
            if (registro.getHoraSalida() != null && registro.getHoraSalida().toLocalDate().equals(fecha)) {
                double totalPagar = registro.getTotalPagar();
                totalDiario += totalPagar;

                if (registro.getVehiculo() instanceof MotoClasica) {
                    totalesPorTipo.put("Clasica", totalesPorTipo.get("Clasica") + totalPagar);
                    conteoPorTipo.put("Clasica", conteoPorTipo.get("Clasica") + 1);
                } else if (registro.getVehiculo() instanceof MotoHibrida) {
                    totalesPorTipo.put("Hibrida", totalesPorTipo.get("Hibrida") + totalPagar);
                    conteoPorTipo.put("Hibrida", conteoPorTipo.get("Hibrida") + 1);
                } else if (registro.getVehiculo() instanceof Carro) {
                    totalesPorTipo.put("Carro", totalesPorTipo.get("Carro") + totalPagar);
                    conteoPorTipo.put("Carro", conteoPorTipo.get("Carro") + 1);
                }
            }
        }

        return String.format(
                "Reporte Diario (%s):\nTotal Clasica: $%.2f (%d vehículos)\nTotal Hibrida: $%.2f (%d vehículos)\nTotal Carro: $%.2f (%d vehículos)\nTotal Recaudado: $%.2f",
                fecha, totalesPorTipo.get("Clasica"), conteoPorTipo.get("Clasica"),
                totalesPorTipo.get("Hibrida"), conteoPorTipo.get("Hibrida"),
                totalesPorTipo.get("Carro"), conteoPorTipo.get("Carro"),
                totalDiario);
    }

    // Método para generar el reporte mensual del dinero recaudado
    // en el parqueadero en un mes seleccionado
    public double generarReporteMensual(YearMonth mes) {
        double totalMensual = 0;
        for (Registro registro : historialVehiculos) {
            if (registro.getHoraSalida() != null && YearMonth.from(registro.getHoraSalida()).equals(mes)) {
                totalMensual += registro.getTotalPagar();
            }
        }
        return totalMensual;
    }
}
