package co.edu.uniquindio.poo;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class Puesto {
    private final int fila;
    private final int columna;
    private Vehiculo[][] listaVehiculos;
    private String listaReporte;

    public Puesto(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.listaVehiculos = new Vehiculo[fila][columna];
        this.listaReporte = "";
    }

    public int getJ() {
        return columna;
    }

    public int getI() {
        return fila;
    }

    public Vehiculo[][] getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(Vehiculo[][] listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    @Override
    public String toString() {
        return "Puesto [j=" + columna + ", i=" + fila + ", estado=" + ", listaVehiculos="
                + Arrays.toString(listaVehiculos) + "]";
    }

    // Metodo que agrega un vehiculo nuevo a la matriz de puestos
    public void agregarVehiculo(Vehiculo vehiculo) {
        boolean vehiculoAgregado = false;
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                if (listaVehiculos[i][j] == null) {
                    listaVehiculos[i][j] = vehiculo;
                    System.out.println("Se ha agregado exitosamente el vehículo " + vehiculo + " en la fila " + i
                            + ", columna " + j);
                    reporteDia();
                    vehiculoAgregado = true;
                    break; // Salir del bucle interno cuando se haya agregado
                } else {
                    System.out.println("No hay espacio disponible para estacionar el vehículo " + vehiculo + "en fila: "
                            + i + ", columna: " + j);
                }
            }
            if (vehiculoAgregado) {
                break; // Salir del bucle externo si el vehículo ya se agrego exitosamente
            }
        }
    }

    // Metodo para obtener la tarifa asociada a un vehiculo segun su placa
    public int obtenerTarifa(String placa) {
        int tarifa = 0;
        for (int i = 0; i < listaVehiculos.length; i++) {
            for (int j = 0; j < listaVehiculos.length; j++) {
                if (listaVehiculos[i][j] != null && listaVehiculos[i][j].getPlaca().equals(placa)) {
                    System.out.println("se encontro " + placa);
                    tarifa = listaVehiculos[i][j].obtenerTarifa();
                    break;
                }
            }
        }
        return tarifa;
    }

    // metodo para encontrar y devolver el propietario de un vehículo en una
    // posición específica dentro de una matriz de vehículos
    public String obtenerDueño(int fila, int columna) {
        // Verificar si la posición es válida y no es nula
        if (listaVehiculos[fila][columna] != null) {
            // Obtener el propietario del vehículo en la posición especificada
            return "El dueño del vehículo en el puesto " + fila + "," + columna + " es: "
                    + listaVehiculos[fila][columna].getPropietario();
        }
        // Retornar un mensaje si no hay vehículo en la posición especificada
        return "No hay vehículo en el puesto " + fila + "," + columna;
    }

    // metodo que devuelve información sobre un vehículo ubicado en una posición
    // específica dentro de una matriz de vehículos
    public String obtenerVehiculo(int fila, int columna) {
        // Verificar si la posición es válida y no es nula
        if (listaVehiculos[fila][columna] != null) {
            // Obtener la placa y el modelo del vehículo en la posición especificada
            return "El vehículo en el puesto " + fila + "," + columna + " es: "
                    + listaVehiculos[fila][columna].getPlaca() + " y su modelo: "
                    + listaVehiculos[fila][columna].getModelo();
        }
        // Retornar un mensaje si no hay vehículo en la posición especificada
        return "No hay vehículo en el puesto " + fila + "," + columna;
    }

    // Metodo para saber si un puesto esta disponible o no
    public String obtenerDisponibilidadPuesto(int fila, int columna) {
        // Verificar si la posición es válida y si está ocupada o libre
        if (listaVehiculos[fila][columna] != null) {
            // El puesto está ocupado
            return "El puesto " + (fila + 1) + "," + (columna + 1) + " está ocupado";
        } else {
            // El puesto está libre
            return "El puesto " + (fila + 1) + "," + (columna + 1) + " está libre";
        }
    }

    // Metodo para obtener el registro de los vehiculos que estan en le parqueadero
    public String registro() {
        String registro = "";
        for (int i = 0; i < listaVehiculos.length; i++) {
            for (int j = 0; j < listaVehiculos.length; j++) {
                registro = registro + obtenerDueño(i, j) + obtenerVehiculo(i, j) + obtenerDisponibilidadPuesto(i, j)
                        + "\n";
            }
        }
        return registro;
    }

    // Metodo para retirar un vehicuo de la matriz
    public void retirarVehiculo(String placa) {
        for (int i = 0; i < listaVehiculos.length; i++) {
            for (int j = 0; j < listaVehiculos[i].length; j++) {
                if (listaVehiculos[i][j] != null && listaVehiculos[i][j].getPlaca().equals(placa)) {
                    listaVehiculos[i][j] = null;
                    System.out.println("Se encontró y se retiró el vehiculo : " + placa);
                    reporteDia();
                    return;
                }
            }
        }
        System.out.println("No se encontró el vehículo con esa placa: " + placa);
    }

    // Metodo para obtener el reporte del dia de los movimientos de los vehiculos
    // del parqueadero

    public void reporteDia() {
        StringBuilder reporte = new StringBuilder("Hubo movimiento a la hora: " + LocalDateTime.now() + "\n");
        for (int i = 0; i < listaVehiculos.length; i++) {
            for (int j = 0; j < listaVehiculos[i].length; j++) {
                if (listaVehiculos[i][j] != null) {
                    reporte.append(listaVehiculos[i][j].toString()).append("\n");
                } else {
                    reporte.append("Puesto ").append(i + 1).append(",").append(j + 1).append(" vacío.\n");
                }
            }
        }
        listaReporte += reporte.toString();
    }

    public String getListaReporte() {
        return listaReporte;
    }

    public void setListaReporte(String listaReporte) {
        this.listaReporte = listaReporte;
    }

    // metodo para actualizar la hora de salida de un vehiculo
    public void horaSalida(LocalTime horaSalida, String placa) {
        boolean vehiculoEncontrado = false;

        for (int i = 0; i < listaVehiculos.length; i++) {
            for (int j = 0; j < listaVehiculos[i].length; j++) {
                if (listaVehiculos[i][j] != null && listaVehiculos[i][j].getPlaca().equals(placa)) {
                    listaVehiculos[i][j].setHoraSalida(horaSalida);
                    System.out.println("El vehículo con las placas: " + placa + " fue despachado");
                    vehiculoEncontrado = true;
                    break; // Salir del bucle interno
                }
            }
            if (vehiculoEncontrado) {
                break; // Salir del bucle externo
            }
        }
        if (!vehiculoEncontrado) {
            System.out.println("El vehículo con las placas: " + placa + " no existe");
        }
    }
}
