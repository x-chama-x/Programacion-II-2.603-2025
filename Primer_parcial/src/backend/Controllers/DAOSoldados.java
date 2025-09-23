package backend.Controllers;

import Conections.BDSoldados;
import backend.Models.Soldado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOSoldados {

    private ValidadorDNI validadorDNI;

    public DAOSoldados() {
        this.validadorDNI = new ValidadorDNI();
    }

    public void crearSoldado(Soldado s) {
        if (!validadorDNI.esFormatoValido(s.getDni())) {
            System.out.println("ERROR: El formato del DNI no es válido. Debe tener entre 7 y 8 dígitos.");
            return;
        }

        // Obtener listas actuales para validación
        List<Soldado> soldados = leerSoldados();
        DAOOficiales daoOficiales = new DAOOficiales();
        List<backend.Models.Oficial> oficiales = daoOficiales.leerOficiales();

        if (validadorDNI.existeDNI(s.getDni(), soldados, oficiales)) {
            System.out.println("ERROR: El DNI " + s.getDni() + " ya está registrado en el sistema.");
            System.out.println(validadorDNI.obtenerInfoDNI(s.getDni(), soldados, oficiales));
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDSoldados.getARCHIVO(), true))) {
            bw.write(s.toString());
            bw.newLine();
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            System.out.println("Soldado agregado...");
        }
    }

    public boolean existe(String dni) {
        boolean encontrado = false;
        List<Soldado> lista = leerSoldados();
        for (Soldado s : lista) {
            if (s.getDni().equals(dni)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public boolean existePorId(int id) {
        boolean encontrado = false;
        List<Soldado> lista = leerSoldados();
        for (Soldado s : lista) {
            if (s.getId() == id) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    private boolean puedeRegistrarSoldado(String dni) {
        return !existe(dni);
    }

    public List<Soldado> leerSoldados() {
        List<Soldado> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDSoldados.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(Soldado.fromString(linea));
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public void actualizarSoldado(String dni, Soldado nuevo) {
        if (!validadorDNI.esFormatoValido(nuevo.getDni())) {
            System.out.println("ERROR: El formato del DNI no es válido. Debe tener entre 7 y 8 dígitos.");
            return;
        }

        // Si el DNI cambió, verificar que esté disponible
        if (!dni.equals(nuevo.getDni())) {
            List<Soldado> soldados = leerSoldados();
            DAOOficiales daoOficiales = new DAOOficiales();
            List<backend.Models.Oficial> oficiales = daoOficiales.leerOficiales();

            if (validadorDNI.existeDNI(nuevo.getDni(), soldados, oficiales)) {
                System.out.println("ERROR: El DNI " + nuevo.getDni() + " ya está registrado en el sistema.");
                System.out.println(validadorDNI.obtenerInfoDNI(nuevo.getDni(), soldados, oficiales));
                return;
            }
        }

        List<Soldado> lista = leerSoldados();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDSoldados.getARCHIVO()))) {
            for (Soldado s : lista) {
                if (s.getDni().equals(dni)) {
                    bw.write(nuevo.toString());
                } else {
                    bw.write(s.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void actualizarSoldadoPorId(int id, Soldado nuevo) {
        if (!validadorDNI.esFormatoValido(nuevo.getDni())) {
            System.out.println("ERROR: El formato del DNI no es válido. Debe tener entre 7 y 8 dígitos.");
            return;
        }

        // Verificar que el DNI esté disponible para esta actualización
        List<Soldado> soldados = leerSoldados();
        DAOOficiales daoOficiales = new DAOOficiales();
        List<backend.Models.Oficial> oficiales = daoOficiales.leerOficiales();

        if (!validadorDNI.esDNIDisponibleParaActualizacion(nuevo.getDni(), soldados, oficiales, "SOLDADO", id)) {
            System.out.println("ERROR: El DNI " + nuevo.getDni() + " ya está registrado en el sistema.");
            System.out.println(validadorDNI.obtenerInfoDNI(nuevo.getDni(), soldados, oficiales));
            return;
        }

        List<Soldado> lista = leerSoldados();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDSoldados.getARCHIVO()))) {
            for (Soldado s : lista) {
                if (s.getId() == id) {
                    bw.write(nuevo.toString());
                } else {
                    bw.write(s.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public String buscarPorDni(String dni) {
        String resultado = "Soldado no encontrado...";
        List<Soldado> lista = leerSoldados();
        for (Soldado s : lista) {
            if (s.getDni().equals(dni)) {
                resultado = s.toString();
                break;
            }
        }
        return resultado;
    }

    public Soldado obtenerPorDni(String dni) {
        List<Soldado> lista = leerSoldados();
        for (Soldado s : lista) {
            if (s.getDni().equals(dni)) {
                return s;
            }
        }
        return null;
    }

    public Soldado obtenerPorId(int id) {
        List<Soldado> lista = leerSoldados();
        for (Soldado s : lista) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public List<Soldado> obtenerSoldadosPorCuartel(int cuartelCodigo) {
        List<Soldado> soldadosCuartel = new ArrayList<>();
        List<Soldado> lista = leerSoldados();
        for (Soldado s : lista) {
            if (s.getCuartelCodigo() == cuartelCodigo) {
                soldadosCuartel.add(s);
            }
        }
        return soldadosCuartel;
    }

    public List<Soldado> obtenerSoldadosPorOficial(int oficialId) {
        List<Soldado> soldadosOficial = new ArrayList<>();
        List<Soldado> lista = leerSoldados();
        for (Soldado s : lista) {
            if (s.getOficialId() == oficialId) {
                soldadosOficial.add(s);
            }
        }
        return soldadosOficial;
    }

    public void eliminarSoldado(String dni) {
        List<Soldado> lista = leerSoldados();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDSoldados.getARCHIVO()))) {
            for (Soldado s : lista) {
                if (!s.getDni().equals(dni)) {
                    bw.write(s.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarSoldadoPorId(int id) {
        List<Soldado> lista = leerSoldados();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDSoldados.getARCHIVO()))) {
            for (Soldado s : lista) {
                if (s.getId() != id) {
                    bw.write(s.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
